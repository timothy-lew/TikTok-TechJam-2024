package com.example.backend.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseDTO {

    private String id;
    private String sellerProfileId;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String imageUrl;
    // TODO: Include sellerWalletAddress, tell frontend guys to show in the UI e.g., 'send TokToken to this seller address'.
}
