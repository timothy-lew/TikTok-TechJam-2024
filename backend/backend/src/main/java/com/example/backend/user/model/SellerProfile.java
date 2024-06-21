package com.example.backend.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
    private String userId;
    private String businessName;
    private String businessDescription;
}