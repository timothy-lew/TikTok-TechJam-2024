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
import com.example.backend.wallet.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ItemRepository itemRepository;
    private final WalletService walletService;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, ItemRepository itemRepository, WalletService walletService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.itemRepository = itemRepository;
        this.walletService = walletService;
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

        // Check that sufficient item quantity is available
        if (item.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Insufficient quantity");
        }

        // Calculate total amount
        float totalAmount = item.getPrice() * dto.getQuantity();

        // Deduct balance from buyer's wallet and start listening to the queue from blockchain to check if buyer sends successfully.
        // If buyer doesn't have sufficient balance (cash/toktoken), exception is thrown.
        walletService.handlePurchase(dto.getBuyerProfileId(), dto.getSellerProfileId(), BigDecimal.valueOf(totalAmount), Transaction.PurchaseType.valueOf(dto.getPurchaseType()));

        // Create and populate the transaction
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPrice(item.getPrice());
        transaction.setTotalAmount(totalAmount);

        // Update item balance
        item.setQuantity(item.getQuantity() - dto.getQuantity());
        itemRepository.save(item);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    public TransactionResponseDTO createTopUpTransaction(TopUpTransactionDTO dto) {
        // TODO: Handle gift card and credit card top-up types
        walletService.handleTopUp(dto.getUserId(), BigDecimal.valueOf(dto.getTopUpAmount()));
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    public TransactionResponseDTO createConversionTransaction(ConversionTransactionDTO dto) {
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal convertedAmount = BigDecimal.ZERO;
        if (dto.getConversionType().equals("CASH_TO_TOKTOKEN")) {
            amount = BigDecimal.valueOf(dto.getCashBalance());
            convertedAmount = amount.multiply(BigDecimal.valueOf(dto.getConversionRate()));
        } else if (dto.getConversionType().equals("TOKTOKEN_TO_CASH")) {
            amount = BigDecimal.valueOf(dto.getTokTokenBalance());
            convertedAmount = amount.multiply(BigDecimal.valueOf(dto.getConversionRate()));
        }

        // Pass both original and converted amounts to handleConvert
        walletService.handleConvert(dto.getUserId(), amount, convertedAmount, Transaction.ConversionType.valueOf(dto.getConversionType()));

        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());

        // Setting the balances correctly based on conversion type
        if (dto.getConversionType().equals("CASH_TO_TOKTOKEN")) {
            transaction.setCashBalance(dto.getCashBalance());
            transaction.setTokTokenBalance(convertedAmount.floatValue());
        } else if (dto.getConversionType().equals("TOKTOKEN_TO_CASH")) {
            transaction.setTokTokenBalance(dto.getTokTokenBalance());
            transaction.setCashBalance(convertedAmount.floatValue());
        }

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

}
