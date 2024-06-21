package com.example.backend.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * TopUpTransaction class to store the top-up transaction details.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopUpTransaction extends Transaction {
    private TopUpTransactionType topUpTransactionType;
    private Float topUpAmount;

    public enum TopUpTransactionType {
        GIFT_CARD,
        CREDIT_CARD,
    }
}
