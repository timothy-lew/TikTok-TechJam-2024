package com.example.backend.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ConversionTransaction class to store the conversion transaction details (between crypto and cash).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionTransaction extends Transaction {
    private Float conversionRate;
    private Float cashBalance;
    private Float coinBalance;
}
