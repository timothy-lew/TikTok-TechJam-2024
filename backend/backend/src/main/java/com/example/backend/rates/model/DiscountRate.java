package com.example.backend.rates.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DiscountRate class to store the discount rate of all items by a seller or a store-wide global discount, irrespective of seller.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "discountRates")
public class DiscountRate {
    @Id
    private String id;
    private String sellerProfileId; // null if it's a global discount
    private float rate; // 0 to 100, in percentage points
    private List<ApplyTo> applyTo;
    private LocalDateTime timestamp;

    public enum ApplyTo {
        PRICE,
        TOKTOKEN_PRICE
    }
}

