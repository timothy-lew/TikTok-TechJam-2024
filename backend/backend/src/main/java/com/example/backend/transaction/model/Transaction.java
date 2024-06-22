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
    // Purchase transaction details
    private String buyerProfileId;
    private String sellerProfileId;
    private String itemId;
    private Float price;
    private Integer quantity;
    private Float totalAmount;
    // Top-up transaction details
    private TopUpTransactionType topUpTransactionType;
    private Float topUpAmount;
    // Conversion transaction details TODO: Potentially removing this endpoint and dto, since wallet controller will handle this, only provide service method to create transaction.
    private Float conversionRate;
    private Float cashBalance;
    private Float coinBalance;

    public enum TransactionType {
        PURCHASE,
        CONVERSION,
        TOPUP
    }

    public enum TopUpTransactionType {
        GIFT_CARD,
        CREDIT_CARD,
    }
}
