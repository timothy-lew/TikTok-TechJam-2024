package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTransactionDTO extends TransactionDTO {
    private String buyerProfileId;
    private String sellerProfileId;
    private String itemId;
    private Float price;
    private Integer quantity;
    private Float totalAmount;
}
