package com.example.backend.user.model;

import com.example.backend.wallet.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashSet;
import java.util.Set;

/**
 * User class to store the user details.
 * One-to-one relationship with Wallet entity.
 * One-to-one relationship with BuyerProfile entity.
 * One-to-one relationship with SellerProfile entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String name;
    private String password;
    private Set<Role> roles = new HashSet<>(); // A user can have multiple roles
    @DocumentReference(lazy = true)
    private BuyerProfile buyerProfile;
    @DocumentReference(lazy = true)
    private SellerProfile sellerProfile;
    @DBRef
    private Wallet wallet;

    public enum Role {
        ROLE_BUYER,
        ROLE_SELLER
    }
}
