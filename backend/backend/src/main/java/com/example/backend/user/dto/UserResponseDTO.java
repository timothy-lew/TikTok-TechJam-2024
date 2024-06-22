package com.example.backend.user.dto;

import com.example.backend.wallet.dto.WalletResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Contains common user details and additional ids to query for more information if needed.
 */
@Getter
@Setter
public class UserResponseDTO {
    // Basic user details
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    private BuyerProfileResponseDTO buyerProfile;
    private SellerProfileResponseDTO sellerProfile;
    private WalletResponseDTO wallet;
}