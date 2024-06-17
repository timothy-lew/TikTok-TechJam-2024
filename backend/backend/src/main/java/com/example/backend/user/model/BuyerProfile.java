package com.example.backend.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * BuyerProfile class to store the buyer profile details of the user.
 * One-to-one relationship with User entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "buyer_profiles")
public class BuyerProfile {

    @Id
    private String id;
    private List<String> shippingAddresses = new ArrayList<>();
    private List<String> billingAddresses = new ArrayList<>();
    private String defaultPaymentMethod;
    //    @DocumentReference(lazy = true)
//    private List<Transaction> purchaseHistory = new ArrayList<>();
    private User user;
}