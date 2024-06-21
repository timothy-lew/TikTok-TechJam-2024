package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionTransactionDTO extends TransactionDTO {
    private Float conversionRate;
    private Float cashBalance;
    private Float coinBalance;
}