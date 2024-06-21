package com.example.backend.transaction.service;

import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.mapper.TransactionMapper;
import com.example.backend.transaction.model.ConversionTransaction;
import com.example.backend.transaction.model.PurchaseTransaction;
import com.example.backend.transaction.model.TopUpTransaction;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionResponseDTO> getUserTransactions(String userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream()
                .map(transactionMapper::fromTransactiontoTransactionResponseDTO)
                .collect(Collectors.toList());
    }

    public boolean createPurchaseTransaction(PurchaseTransactionDTO dto) {
        PurchaseTransaction purchaseTransaction = (PurchaseTransaction) transactionMapper.toEntity(dto);
        purchaseTransaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(purchaseTransaction);
        // TODO: Make sure transactions are created for both buyer and seller
        // Probably need to set buyerprofile and sellerprofile transactions!
        return true;
    }

    public boolean createTopUpTransaction(TopUpTransactionDTO dto) {
        TopUpTransaction topUpTransaction = (TopUpTransaction) transactionMapper.toEntity(dto);
        topUpTransaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(topUpTransaction);
        return true;
    }

    public boolean createConversionTransaction(ConversionTransactionDTO dto) {
        ConversionTransaction conversionTransaction = (ConversionTransaction) transactionMapper.toEntity(dto);
        conversionTransaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(conversionTransaction);
        return true;
    }
}
