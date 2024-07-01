package com.example.backend.item.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemResponseDTO {

    private String id;
    private String sellerProfileId;
    private String name;
    private String description;
    private Float price;
    private Float tokTokenPrice;
    private String discountRate;
    private List<String> discountAppliesTo;
    private Float discountedPrice;
    private Float discountedTokTokenPrice;
    private Integer quantity;
    private String imageUrl;
    private String businessName;
    private String sellerWalletAddress;
}
