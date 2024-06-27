package com.example.backend.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionTransactionDTO {
    private String userId;
    private Float cashToConvert;
    private Float tokTokenToConvert;
    @NotBlank(message = "Conversion type is required")
    private String conversionType;
}