package com.example.backend.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Transaction class to store the common transaction details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private String userId;

    public enum TransactionType {
        PURCHASE,
        CONVERSION,
        TOPUP
    }
}
