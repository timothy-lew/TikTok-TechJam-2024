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
    private WithdrawDetails withdrawDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseDetails {
        private String buyerProfileId;
        private String buyerUserName;
        private String sellerProfileId;
        private String sellerBusinessName;
        private String itemId;
        private Integer quantity;
        private Float purchaseAmount;
        private String purchaseType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopUpDetails {
        private String topUpType;
        private Float topUpAmount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversionDetails {
        private Float conversionRate;
        private Float cashToConvert;
        private Float tokTokenToConvert;
        private Float convertedAmount;
        private String conversionType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WithdrawDetails {
        private Float withdrawAmount;
    }
}
