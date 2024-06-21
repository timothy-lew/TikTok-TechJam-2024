package com.example.backend.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopUpTransactionDTO {
    private String userId;
    private String topUpTransactionType;
    private Float topUpAmount;
}