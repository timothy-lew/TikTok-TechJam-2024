package com.example.backend.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * BuyerProfile class to store the buyer profile details of the user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buyer_profiles")
public class BuyerProfile {

    @Id
    private String id;
    private String userId;
    private String shippingAddress;
    private String billingAddress;
    private String defaultPaymentMethod;
}