package com.example.backend.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyerProfileResponseDTO {
    private String id;
    private String shippingAddress;
    private String billingAddress;
    private String defaultPaymentMethod;
}
