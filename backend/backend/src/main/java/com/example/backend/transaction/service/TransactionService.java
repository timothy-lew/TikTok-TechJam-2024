package com.example.backend.transaction.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.transaction.dto.ConversionTransactionDTO;
import com.example.backend.transaction.dto.PurchaseTransactionDTO;
import com.example.backend.transaction.dto.TopUpTransactionDTO;
import com.example.backend.transaction.dto.TransactionResponseDTO;
import com.example.backend.transaction.mapper.TransactionMapper;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.transaction.repository.TransactionRepository;
import com.example.backend.user.model.User;
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
    private final CommonValidationAndGetService commonValidationAndGetService;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper, ItemRepository itemRepository, WalletService walletService, CommonValidationAndGetService commonValidationAndGetService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.itemRepository = itemRepository;
        this.walletService = walletService;
        this.commonValidationAndGetService = commonValidationAndGetService;
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

        // Calculate total amount based on purchase type
        float totalAmount;
        if (Transaction.PurchaseType.CASH.equals(Transaction.PurchaseType.valueOf(dto.getPurchaseType()))) {
            totalAmount = item.getPrice() * dto.getQuantity();
        } else if (Transaction.PurchaseType.TOK_TOKEN.equals(Transaction.PurchaseType.valueOf(dto.getPurchaseType()))) {
            // Fetch current conversion rate
            float conversionRate = commonValidationAndGetService.validateAndGetCurrentConversionRate().getRate();
            totalAmount = item.getPrice() * dto.getQuantity() * conversionRate;
        } else {
            throw new RuntimeException("Unsupported purchase type");
        }

        // Deduct balance from buyer's wallet
        walletService.handlePurchase(dto.getBuyerProfileId(), dto.getSellerProfileId(), BigDecimal.valueOf(totalAmount), Transaction.PurchaseType.valueOf(dto.getPurchaseType()));

        // Create and populate the transaction
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPrice(item.getPrice());
        transaction.setTotalAmount(totalAmount);

        // Update item balance
        item.setQuantity(item.getQuantity() - dto.getQuantity());
        itemRepository.save(item);

        // Save transaction
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }


    public TransactionResponseDTO createTopUpTransaction(TopUpTransactionDTO dto) {
        // TODO: Handle gift card and credit card top-up types (credit card one just simplify it)
        walletService.handleTopUp(dto.getUserId(), BigDecimal.valueOf(dto.getTopUpAmount()));
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());

        // For ease of querying transactions by buyer/seller profile ID
        User user = commonValidationAndGetService.validateAndGetUser(dto.getUserId());
        if (user.getRoles().contains(User.Role.ROLE_BUYER)) {
            transaction.setBuyerProfileId(user.getBuyerProfile().getId());
            transaction.setSellerProfileId(null);
        } else if (user.getRoles().contains(User.Role.ROLE_SELLER)) {
            transaction.setBuyerProfileId(null);
            transaction.setSellerProfileId(user.getSellerProfile().getId());
        }

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    public TransactionResponseDTO createConversionTransaction(ConversionTransactionDTO dto) {
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal convertedAmount = BigDecimal.ZERO;
        float conversionRate = commonValidationAndGetService.validateAndGetCurrentConversionRate().getRate();
        float inverseConversionRate = commonValidationAndGetService.validateAndGetCurrentConversionRate().getInverseRate();

        if (dto.getConversionType().equals("CASH_TO_TOKTOKEN")) {
            amount = BigDecimal.valueOf(dto.getCashToConvert());
            convertedAmount = amount.multiply(BigDecimal.valueOf(conversionRate));
        } else if (dto.getConversionType().equals("TOKTOKEN_TO_CASH")) {
            amount = BigDecimal.valueOf(dto.getTokTokenToConvert());
            convertedAmount = amount.multiply(BigDecimal.valueOf(inverseConversionRate));
        }

        // Pass both original and converted amounts to handleConvert
        walletService.handleConvert(dto.getUserId(), amount, convertedAmount, Transaction.ConversionType.valueOf(dto.getConversionType()));

        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());

        // Setting the balances based on conversion type
        if (dto.getConversionType().equals("CASH_TO_TOKTOKEN")) {
            transaction.setConversionRate(conversionRate);
            transaction.setCashToConvert(dto.getCashToConvert());
            transaction.setTokTokenToConvert(null);
            transaction.setConvertedAmount(convertedAmount.floatValue());
        } else if (dto.getConversionType().equals("TOKTOKEN_TO_CASH")) {
            transaction.setConversionRate(inverseConversionRate);
            transaction.setCashToConvert(null);
            transaction.setTokTokenToConvert(dto.getTokTokenToConvert());
            transaction.setConvertedAmount(convertedAmount.floatValue());
        }

        // For ease of querying transactions by buyer/seller profile ID
        User user = commonValidationAndGetService.validateAndGetUser(dto.getUserId());
        if (user.getRoles().contains(User.Role.ROLE_BUYER)) {
            transaction.setBuyerProfileId(user.getBuyerProfile().getId());
            transaction.setSellerProfileId(null);
        } else if (user.getRoles().contains(User.Role.ROLE_SELLER)) {
            transaction.setBuyerProfileId(null);
            transaction.setSellerProfileId(user.getSellerProfile().getId());
        }

        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

}
