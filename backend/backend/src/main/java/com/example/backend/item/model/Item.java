package com.example.backend.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* Item class to store the item details listed by the seller.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "items")
public class Item {

    @Id
    private String id;
    private String sellerProfileId;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private Binary image; // MongoDB requires Binary type for storing images
    private String businessName;
    private String sellerWalletAddress;
}
