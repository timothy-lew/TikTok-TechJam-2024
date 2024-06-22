package com.example.backend.transaction.service;

import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.mapper.TransactionMapper;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ItemRepository itemRepository;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, ItemRepository itemRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.itemRepository = itemRepository;
    }

    public List<TransactionResponseDTO> getBuyerTransactions(String buyerProfileId) {
        List<Transaction> transactions = transactionRepository.findByBuyerProfileId(buyerProfileId);
        return transactions.stream()
                .map(transactionMapper::fromTransactiontoTransactionResponseDTO)
                .toList();
    }

    public List<TransactionResponseDTO> getSellerTransactions(String sellerProfileId) {
        List<Transaction> transactions = transactionRepository.findBySellerProfileId(sellerProfileId);
        return transactions.stream()
                .map(transactionMapper::fromTransactiontoTransactionResponseDTO)
                .toList();
    }

    public TransactionResponseDTO createPurchaseTransaction(PurchaseTransactionDTO dto) {
        // Fetch the item to get its price
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Calculate total amount
        float totalAmount = item.getPrice() * dto.getQuantity();

        // Create and populate the transaction
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPrice(item.getPrice());
        transaction.setTotalAmount(totalAmount);

        // TODO: Call item package service to deduct item quantity
        // TODO: Call respective WalletService method that handles cashBalance deduction

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    public TransactionResponseDTO createTopUpTransaction(TopUpTransactionDTO dto) {
        // TODO: Call respective WalletService method that handles cashBalance top-up
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    // TODO: Potentially removing this endpoint and dto, since wallet controller will handle this, only provide service method to create transaction.
    public TransactionResponseDTO createConversionTransaction(ConversionTransactionDTO dto) {
        // TODO: Allow wallet service to call this method after successful conversion so that we can create a transaction record
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }
}
