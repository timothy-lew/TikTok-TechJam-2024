package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionTransactionDTO {
    private String userId;
    private Float conversionRate;
    private Float cashBalance;
    private Float tokTokenBalance;
    private String conversionType;
}