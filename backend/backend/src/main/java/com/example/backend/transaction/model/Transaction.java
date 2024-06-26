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
    private String userId; // Needed by top-up and conversion transactions, not needed by purchase transactions
    // Although these 2 fields are not needed by top-up and conversion transactions, they are still included in the model
    // This is so that our getTransactionByBuyerProfileId() and getTransactionBySellerProfileId() methods in TransactionService
    // can return the transactions based on these fields.
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
    // Conversion transaction details
    private Float conversionRate;
    private Float cashBalance;
    private Float tokTokenBalance;
    private ConversionType conversionType;

    public enum TransactionType {
        PURCHASE,
        CONVERSION,
        TOPUP
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
