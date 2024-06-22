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
    private String id;
    private String transactionType;
    private String transactionDate;
    private String userId;
    private PurchaseDetails purchaseDetails;
    private TopUpDetails topUpDetails;
    private ConversionDetails conversionDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseDetails {
        private String buyerProfileId;
        private String sellerProfileId;
        private String itemId;
        private Integer quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopUpDetails {
        private String topUpTransactionType;
        private Float topUpAmount;
    }

    // TODO: Potentially removing this endpoint and dto, since wallet controller will handle this, only provide service method to create transaction.
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversionDetails {
        private Float conversionRate;
        private Float cashBalance;
        private Float coinBalance;
    }
}
