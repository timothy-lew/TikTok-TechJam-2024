package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Potentially removing this endpoint and dto, since wallet controller will handle this, only provide service method to create transaction.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionTransactionDTO {
    private String userId;
    private Float conversionRate;
    private Float cashBalance;
    private Float coinBalance;
}