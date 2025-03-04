package com.example.backend.user.dto;

import com.example.backend.user.validation.ValidProfileFields;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Contains fields for CRUD operations on user.
 * Buyer and seller profile fields are populated only during account creation.
 * Updates on buyer and seller profiles are done separately, not through this DTO.
 */
@Getter
@Setter
@ValidProfileFields // handles validation for buyer & seller fields
public class UserDTO {
    private String id;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Password is required")
    private String password;
    @NotEmpty(message = "At least one role is required")
    private Set<String> roles;
    // Buyer profile fields indicated once in sign up form, if any.
    private String shippingAddress;
    private String billingAddress;
    // Seller profile fields indicated once in sign up form, if any.
    private String businessName;
    private String businessDescription;
    // Wallet profile field
    private String walletAddress;
}