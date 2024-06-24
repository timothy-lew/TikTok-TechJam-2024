package com.example.backend.contract.service;

import com.example.backend.contract.TOKToken;
import com.example.backend.contract.dto.SendCryptoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
    @Value("${web3.rpc.url}")
//    private String rpcUrl;
    private String rpcUrl = "http://127.0.0.1:8545";

    @Value("${web3.contract.address}")
//    private String contractAddress;
    private String contractAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3";

    @Value("${web3.wallet.private-key}")
//    private String privateKey;
    private String privateKey = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80";

    private final Web3j web3j;
    private final TOKToken contract;

    public ContractService() {
        this.web3j = Web3j.build(new HttpService(rpcUrl));
        Credentials credentials = Credentials.create(privateKey);

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
        ContractGasProvider gasProvider = new DefaultGasProvider();
        this.contract = TOKToken.load(contractAddress, web3j, transactionManager, gasProvider);
        this.listen();
    }

    public void listen() {
        Web3j web3j = Web3j.build(new HttpService(rpcUrl));
        String tikTokAddress = "0xf39fd6e51aad88f6f4ce6ab8827279cfffb92266";
        String contractAddress = "0x5FbDB2315678afecb367f032d93F642f64180aa3";

        // Subscribe to token transfer events
        EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        );
        web3j.ethLogFlowable(filter).subscribe(log -> {
            System.out.println("listening");
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
                String cleanTikTokAddress = tikTokAddress.substring(2).toLowerCase();
                if (cleanReceiverAddress.contains(cleanTikTokAddress)) {
                    System.out.println(cleanReceiverAddress + " received " + amountReceivedInEth + " tokens from " + senderAddress);
                    // call alex's service if need be to update any db tables, can query user table by sender address
                    // inform seller to ship goods
                    // we manually transfer TOKTokens to seller because
                    // seller might scam the buyer and there is no way i can get the seller address
                }
            }
        });
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

