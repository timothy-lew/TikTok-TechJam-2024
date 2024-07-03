package com.example.backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerProfileDTO {
    private String userId;
    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;
    @NotBlank(message = "Billing address is required")
    private String billingAddress;
}