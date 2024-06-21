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
}
