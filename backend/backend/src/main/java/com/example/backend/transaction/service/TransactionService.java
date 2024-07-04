package com.example.backend.transaction.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.transaction.dto.*;
import com.example.backend.transaction.exception.InsufficientItemQuantityException;
import com.example.backend.transaction.mapper.TransactionMapper;
import com.example.backend.transaction.model.GiftCard;
import com.example.backend.transaction.model.Transaction;
import com.example.backend.transaction.repository.TransactionRepository;
import com.example.backend.user.model.User;
import com.example.backend.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ItemRepository itemRepository;
    private final WalletService walletService;
    private final CommonValidationAndGetService commonValidationAndGetService;
    private final GiftCardService giftCardService;

    public List<TransactionResponseDTO> getBuyerTransactions(String buyerProfileId) {
        List<Transaction> transactions = commonValidationAndGetService.validateAndGetTransactionsByBuyerProfileId(buyerProfileId);
        return transactions.stream()
                .map(transactionMapper::fromTransactiontoTransactionResponseDTO)
                .toList();
    }

    public List<TransactionResponseDTO> getSellerTransactions(String sellerProfileId) {
        List<Transaction> transactions = commonValidationAndGetService.validateAndGetTransactionsBySellerProfileId(sellerProfileId);
        return transactions.stream()
                .map(transactionMapper::fromTransactiontoTransactionResponseDTO)
                .toList();
    }

    public TransactionResponseDTO createPurchaseTransaction(PurchaseTransactionDTO dto) {
        // Fetch the item to get its price and discount information
        Item item = commonValidationAndGetService.validateAndGetItem(dto.getItemId());

        // Check that sufficient item quantity is available
        if (item.getQuantity() < dto.getQuantity()) {
            throw new InsufficientItemQuantityException("Insufficient quantity for item: " + dto.getItemId());
        }

        // Calculate total amount based on purchase type and apply discount if available
        float totalAmount;
        if (Transaction.PurchaseType.CASH.equals(Transaction.PurchaseType.valueOf(dto.getPurchaseType()))) {
            float priceToUse = item.getDiscountedPrice() != null ? item.getDiscountedPrice() : item.getPrice();
            totalAmount = priceToUse * dto.getQuantity();
        } else {
            // Fetch current conversion rate
            float priceToUse = item.getDiscountedTokTokenPrice() != null ? item.getDiscountedTokTokenPrice() : item.getTokTokenPrice();
            totalAmount = priceToUse * dto.getQuantity();
        }

        // Deduct balance from buyer's wallet
        walletService.handlePurchase(dto.getBuyerProfileId(), dto.getSellerProfileId(), BigDecimal.valueOf(totalAmount), Transaction.PurchaseType.valueOf(dto.getPurchaseType()));

        // Create and populate the transaction
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setPrice(item.getPrice());
        transaction.setTotalAmount(totalAmount);
        if (transaction.getPurchaseType() == Transaction.PurchaseType.CASH) {
            transaction.setIsPaid(null);
            // Update item balance
            item.setQuantity(item.getQuantity() - dto.getQuantity());
            itemRepository.save(item);
        } else {
            // Update item balance in ContractService
            transaction.setIsPaid(false);
        }


        // Save transaction
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    public TransactionResponseDTO createTopUpTransaction(TopUpTransactionDTO dto) {
        BigDecimal topUpAmount;

        if (Transaction.TopUpType.GIFT_CARD.equals(Transaction.TopUpType.valueOf(dto.getTopUpType()))) {
            GiftCard giftCard = giftCardService.validateGiftCard(dto.getGiftCardCode());
            // Retrieve the gift card value
            topUpAmount = BigDecimal.valueOf(giftCard.getValue());
            // Mark the gift card as used
            giftCardService.markGiftCardAsUsed(giftCard);
            dto.setTopUpAmount(giftCard.getValue());
        } else {
            topUpAmount = BigDecimal.valueOf(dto.getTopUpAmount());
        }

        walletService.handleTopUp(dto.getUserId(), topUpAmount);
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setIsPaid(null);

        // For ease of querying transactions by buyer/seller profile ID
        User user = commonValidationAndGetService.validateAndGetUser(dto.getUserId());
        if (user.getRoles().contains(User.Role.ROLE_BUYER)) {
            transaction.setBuyerProfileId(user.getBuyerProfile().getId());
            transaction.setSellerProfileId(null);
        } else if (user.getRoles().contains(User.Role.ROLE_SELLER)) {
            transaction.setBuyerProfileId(null);
            transaction.setSellerProfileId(user.getSellerProfile().getId());
        }

        // Manually set the topUpAmount and giftCardCode in the transaction
        transaction.setTopUpAmount(dto.getTopUpAmount());
        if (Transaction.TopUpType.GIFT_CARD.equals(Transaction.TopUpType.valueOf(dto.getTopUpType()))) {
            transaction.setGiftCardCode(dto.getGiftCardCode());
        } else {
            transaction.setGiftCardCode(null);
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
            transaction.setIsPaid(null);
        } else if (dto.getConversionType().equals("TOKTOKEN_TO_CASH")) {
            transaction.setConversionRate(inverseConversionRate);
            transaction.setCashToConvert(null);
            transaction.setTokTokenToConvert(dto.getTokTokenToConvert());
            transaction.setConvertedAmount(convertedAmount.floatValue());
            transaction.setIsPaid(false);
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

    public TransactionResponseDTO createWithdrawTransaction(WithdrawTransactionDTO dto) {
        // Handle the withdrawal
        walletService.handleWithdraw(dto.getUserId(), BigDecimal.valueOf(dto.getWithdrawAmount()));

        // Create the transaction object
        Transaction transaction = transactionMapper.fromDTOtoTransaction(dto);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setIsPaid(null);

        // For ease of querying transactions by buyer/seller profile ID
        User user = commonValidationAndGetService.validateAndGetUser(dto.getUserId());
        if (user.getRoles().contains(User.Role.ROLE_BUYER)) {
            transaction.setBuyerProfileId(user.getBuyerProfile().getId());
            transaction.setSellerProfileId(null);
        } else if (user.getRoles().contains(User.Role.ROLE_SELLER)) {
            transaction.setBuyerProfileId(null);
            transaction.setSellerProfileId(user.getSellerProfile().getId());
        }

        // Save the transaction to the repository
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Map and return the response DTO
        return transactionMapper.fromTransactiontoTransactionResponseDTO(savedTransaction);
    }

    public Boolean checkTransactionStatus(String transactionId) {
        Transaction transaction = commonValidationAndGetService.validateAndGetTransaction(transactionId);
        return transaction.getIsPaid();
    }

}
