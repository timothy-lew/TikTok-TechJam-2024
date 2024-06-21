package com.example.backend.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * PurchaseTransaction class to store the purchase transaction details.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseTransaction extends Transaction {
    private String buyerProfileId;
    private String sellerProfileId;
    private String itemId;
    private Float price;
    private Integer quantity;
    private Float totalAmount;
}
