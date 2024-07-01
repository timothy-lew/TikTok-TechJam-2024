package com.example.backend.rates.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRateResponseDTO {
    private String id;
    private String sellerProfileId; // null if it's a global discount
    private float rate; // 0 to 100, in percentage points
    private List<String> applyTo;
    private LocalDateTime timestamp;
}