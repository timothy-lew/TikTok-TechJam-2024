package com.example.backend.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "giftCards")
public class GiftCard {
    @Id
    private String id;
    private String code;
    private Float value;
    private boolean used;
}
