package com.example.backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerProfileDTO {
    @NotBlank(message = "Business name is required")
    private String businessName;
    @NotBlank(message = "Business description is required")
    private String businessDescription;
}
