package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTransactionDTO {
    private String buyerProfileId;
    private String sellerProfileId;
    private String itemId;
    private Integer quantity;
}
