package com.example.backend.rate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * ConversionRate class to store the conversion rate of cash to tok token, and vice versa.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "conversionRate")
public class ConversionRate {
    @Id
    private String id;
    private float rate; // Cash to TokToken rate
    private float inverseRate; // TokToken to Cash rate
    private LocalDateTime timestamp; // To track the last time the rate was updated
}