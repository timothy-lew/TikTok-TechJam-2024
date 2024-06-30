package com.example.backend.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {

    // Common transaction details
    @Id
    private String id;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    // Needed by purchase and conversion transaction
    private Boolean isPaid;
    // Needed by top-up and conversion transactions
    private String userId;
    // Needed only by purchase transactions, but we still store it for all transaction types.
    // For top-up and conversion transactions, these fields will store the user's respective profile IDs.
    // This is required for the ease of getting transactions by buyer/seller profile ID.
    private String buyerProfileId;
    private String sellerProfileId;
    // Purchase transaction details
    private String itemId;
    private Float price;
    private Integer quantity;
    private Float totalAmount;
    private PurchaseType purchaseType;
    // Top-up transaction details
    private TopUpType topUpType;
    private Float topUpAmount;
    private String giftCardCode;
    // Conversion transaction details
    private Float conversionRate;
    private Float cashToConvert;
    private Float tokTokenToConvert;
    private Float convertedAmount;
    private ConversionType conversionType;
    // Withdraw transaction details
    private Float withdrawAmount;

    public enum TransactionType {
        PURCHASE,
        CONVERSION,
        TOPUP,
        WITHDRAW
    }

    public enum PurchaseType {
        TOK_TOKEN,
        CASH
    }

    public enum TopUpType {
        GIFT_CARD,
        CREDIT_CARD,
    }

    public enum ConversionType {
        CASH_TO_TOKTOKEN,
        TOKTOKEN_TO_CASH
    }
}
