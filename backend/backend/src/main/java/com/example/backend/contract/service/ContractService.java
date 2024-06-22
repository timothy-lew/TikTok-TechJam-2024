package com.example.backend.contract.service;

import com.example.backend.contract.TOKToken;
import com.example.backend.contract.dto.SendCryptoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {

    public SendCryptoDTO sendCrypto(SendCryptoDTO sendCryptoDTO) {
        try {
            TransactionReceipt receipt = transfer(sendCryptoDTO.getAddress(), sendCryptoDTO.getAmount());
            log.info("receipt: {}", receipt);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return sendCryptoDTO;
    }

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
    }

    public TransactionReceipt transfer(String userAddress, Long amountInEth) throws Exception {
        BigDecimal etherAmount = BigDecimal.valueOf(amountInEth);
        BigInteger weiAmount = Convert.toWei(etherAmount, Convert.Unit.ETHER).toBigInteger();
        return contract.transfer(userAddress, weiAmount).send();
    }
}

