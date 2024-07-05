package com.example.backend.contract.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.contract.TOKToken;
import com.example.backend.contract.dto.SendCryptoDTO;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.transaction.repository.TransactionRepository;
import com.example.backend.user.model.BuyerProfile;
import com.example.backend.user.repository.BuyerProfileRepository;
import com.example.backend.wallet.repository.WalletRepository;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContractService {
    private final String rpcUrl;
    private final String contractAddress;
    private final String tikTokAddress;
    private final String privateKey;
    private final Web3j web3j;
    private final TOKToken contract;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final BuyerProfileRepository buyerProfileRepository;
    private final ItemRepository itemRepository;
    private final CommonValidationAndGetService commonValidationAndGetService;
    private Disposable tikTokSubscription;
    private Disposable sellerSubscription;


    public ContractService(@Value("${web3.rpc.url}") String rpcUrl,
                           @Value("${web3.contract.address}") String contractAddress,
                           @Value("${web3.contract.tikTokAddress}") String tikTokAddress,
                           @Value("${web3.wallet.private-key}") String privateKey,
                           WalletRepository walletRepository,
                           TransactionRepository transactionRepository,
                           BuyerProfileRepository buyerProfileRepository,
                           ItemRepository itemRepository,
                           CommonValidationAndGetService commonValidationAndGetService) {
        this.rpcUrl = rpcUrl;
        this.contractAddress = contractAddress;
        this.tikTokAddress = tikTokAddress;
        this.privateKey = privateKey;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.buyerProfileRepository = buyerProfileRepository;
        this.itemRepository = itemRepository;
        this.commonValidationAndGetService = commonValidationAndGetService;

        this.web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);

        ContractGasProvider gasProvider = new DefaultGasProvider();

        this.contract = TOKToken.load(contractAddress, web3j, transactionManager, gasProvider);
    }

    public void listenToTikTokAddress() {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));

        // Subscribe to token transfer events
        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        );
        tikTokSubscription = web3j.ethLogFlowable(filter).subscribe(log -> {
            System.out.println("listening to tiktok address");
            List<String> topics = log.getTopics();
            String contractAddress2 = topics.get(0);

            if (topics.size() > 0 && contractAddress2.equals(EventEncoder.encode(TOKToken.TRANSFER_EVENT))) {
                String senderAddress = topics.get(1);
                String receiverAddress = topics.get(2);

                String amountInHex = log.getData();
                String cleanHex = amountInHex.replaceFirst("0x", "");
                BigInteger weiAmount = new BigInteger(cleanHex, 16);
                BigInteger amountReceivedInEth = Convert.fromWei(weiAmount.toString(), Convert.Unit.ETHER).toBigInteger();

                String cleanReceiverAddress = receiverAddress.substring(2).toLowerCase();

                // Remove the '0x' prefix and convert to lowercase
                String cleanSenderAddress = senderAddress.substring(2).toLowerCase();
                System.out.println("cleanSenderAddress = " + cleanSenderAddress);
                // Remove leading zeros and add the '0x' prefix back, required for MongoDB query
                String processedSenderAddress = "0x" + cleanSenderAddress.replaceFirst("^0+(?!$)", "");
                System.out.println("Processed sender address = " + processedSenderAddress);

                String cleanTikTokAddress = tikTokAddress.substring(2).toLowerCase();
                if (cleanReceiverAddress.contains(cleanTikTokAddress)) {
                    System.out.println(cleanReceiverAddress + " received " + amountReceivedInEth + " tokens from " + senderAddress);
                    stopListenToTikTokAddress();
                    Optional<WalletRepository.UserIdOnly> userId = walletRepository.findUserIdByWalletAddressCaseInsensitive(processedSenderAddress);
                    if (userId.isPresent()) {
                        Transaction tx = transactionRepository.findFirstByUserIdOrderByTransactionDateDesc(userId.get().getUserId());
                        tx.setIsPaid(true);
                        transactionRepository.save(tx);
                        System.out.println("Transaction ID = " + tx.getId() + " is paid the due amount in TokToken.");
                        System.out.println("USER ID = " + userId.get().getUserId());
                    } else {
                        System.out.println("No user found with this wallet address.");

                    }
                }
            }
        });
    }

    public void listenToSellerAddress(String address) {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));

        // Subscribe to token transfer events
        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        );
        sellerSubscription = web3j.ethLogFlowable(filter).subscribe(log -> {
            System.out.println("listening to " + address);
            List<String> topics = log.getTopics();
            String contractAddress2 = topics.get(0);

            if (topics.size() > 0 && contractAddress2.equals(EventEncoder.encode(TOKToken.TRANSFER_EVENT))) {
                String senderAddress = topics.get(1);
                String receiverAddress = topics.get(2);

                String amountInHex = log.getData();
                String cleanHex = amountInHex.replaceFirst("0x", "");
                BigInteger weiAmount = new BigInteger(cleanHex, 16);
                BigInteger amountReceivedInEth = Convert.fromWei(weiAmount.toString(), Convert.Unit.ETHER).toBigInteger();

                String cleanReceiverAddress = receiverAddress.substring(2).toLowerCase();

                // Remove the '0x' prefix and convert to lowercase
                String cleanSenderAddress = senderAddress.substring(2).toLowerCase();
                System.out.println("cleanSenderAddress = " + cleanSenderAddress);
                // Remove leading zeros and add the '0x' prefix back, required for MongoDB query
                String processedSenderAddress = "0x" + cleanSenderAddress.replaceFirst("^0+(?!$)", "");
                System.out.println("Processed sender address = " + processedSenderAddress);

                String cleanTikTokAddress = address.substring(2).toLowerCase();
                if (cleanReceiverAddress.contains(cleanTikTokAddress)) {
                    System.out.println(cleanReceiverAddress + " received " + amountReceivedInEth + " tokens from " + senderAddress);
                    stopListenToSellerAddress();
                    Optional<WalletRepository.UserIdOnly> userId = walletRepository.findUserIdByWalletAddressCaseInsensitive(processedSenderAddress);
                    if (userId.isPresent()) {
                        System.out.println("User ID = " + userId.get().getUserId());
                        Optional<BuyerProfile> buyerProfile = buyerProfileRepository.findByUserId(userId.get().getUserId());
                        if (buyerProfile.isPresent()) {
                            Transaction tx = transactionRepository.findFirstByBuyerProfileIdOrderByTransactionDateDesc(buyerProfile.get().getId());
                            tx.setIsPaid(true);
                            // Update item balance
                            Item item = commonValidationAndGetService.validateAndGetItem(tx.getItemId());
                            item.setQuantity(item.getQuantity() - tx.getQuantity());
                            itemRepository.save(item);
                            System.out.println("**Transaction ID: " + tx.getId());
                            System.out.println(tx.getIsPaid());
                            System.out.println(tx.getTransactionType());
                            System.out.println(tx.getPurchaseType());
                            System.out.printf("is paid = %s\n", tx.getIsPaid() ? "true" : "false");
                            System.out.println("Transaction ID = " + tx.getId() + " is paid the due amount in TokToken.");
                            System.out.println("USER ID = " + userId.get().getUserId());
                            transactionRepository.save(tx);
                        } else {
                            System.out.println("No buyer found with this wallet address.");
                        }
                    } else {
                        System.out.println("No user found with this wallet address.");
                    }
                }
            }
        });
    }

    public void stopListenToTikTokAddress() {
        if (tikTokSubscription != null && !tikTokSubscription.isDisposed()) {
            tikTokSubscription.dispose();
            log.info("Stopped listening to tikTokSubscription");
        }
    }

    public void stopListenToSellerAddress() {
        if (sellerSubscription != null && !sellerSubscription.isDisposed()) {
            sellerSubscription.dispose();
            log.info("Stopped listening to sellerSubscription");
        }
    }

    public BigDecimal sendCrypto(SendCryptoDTO sendCryptoDTO) {
        try {
            transfer(sendCryptoDTO.getAddress(), sendCryptoDTO.getAmount());
            return getBalance(sendCryptoDTO.getAddress());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public TransactionReceipt transfer(String userAddress, Long amountInEth) throws Exception {
        BigDecimal etherAmount = BigDecimal.valueOf(amountInEth);
        BigInteger weiAmount = Convert.toWei(etherAmount, Convert.Unit.ETHER).toBigInteger();
        return contract.transfer(userAddress, weiAmount).send();
    }

    public BigDecimal getBalance(String address) {
        try {
            BigInteger balanceInWei = contract.balanceOf(address).send();
            BigDecimal balanceInEth = Convert.fromWei(balanceInWei.toString(), Convert.Unit.ETHER);
            log.info("address {} has: {} TOKTokens", address, balanceInEth);

            return balanceInEth;
        } catch (Exception e) {
            log.error("Error getting balance: {}", e.getMessage());
            throw new RuntimeException("Error getting balance", e);
        }
    }
}

