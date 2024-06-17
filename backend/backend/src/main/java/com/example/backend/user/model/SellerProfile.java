package com.example.backend.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* SellerProfile class to store the seller profile details of the user.
 * One-to-one relationship with User entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "seller_profiles")
public class SellerProfile {

    @Id
    private String id;
    private String userId;
    private String businessName;
    private String businessDescription;
    //    @DocumentReference(lazy = true)
//    private List<Item> itemsForSale = new ArrayList<>();
//    @DocumentReference(lazy = true)
//    private List<Transaction> salesHistory = new ArrayList<>();
//    private BigDecimal pendingBalance = BigDecimal.ZERO;
    private User user;
}