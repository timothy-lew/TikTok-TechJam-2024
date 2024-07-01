package com.example.backend.rates.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRateDTO {
    private String sellerProfileId; // null if it's a global discount

    @Min(0)
    @Max(100)
    private float rate;

    @NotEmpty
    private List<String> applyTo;
}