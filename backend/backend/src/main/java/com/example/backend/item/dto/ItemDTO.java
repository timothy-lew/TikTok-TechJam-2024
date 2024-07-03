package com.example.backend.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ItemDTO {

    @NotBlank(message = "Item name is required")
    private String name;
    @NotBlank(message = "Item description is required")
    private String description;
    @NotNull(message = "Item price is required")
    private Float price;
    @NotNull(message = "Item quantity is required")
    private Integer quantity;
    private MultipartFile image;
}
