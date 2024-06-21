package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransactionResponseDTO class to store all possible transaction details to be returned to the frontend.
 * Frontend can simply check transactionType to determine the type of transaction, and extract relevant fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    // Basic transaction details
    private String id;
    private String transactionType;
    private String transactionDate;
    private String userId;
    // Purchase transaction details
    private String buyerProfileId;
    private String sellerProfileId;
    private String itemId;
    private Float price;
    private Integer quantity;
    private Float totalAmount;
    // Top-up transaction details
    private String topUpTransactionType;
    private Float topUpAmount;
    // Conversion transaction details
    private Float conversionRate;
    private Float cashBalance;
    private Float coinBalance;
}
