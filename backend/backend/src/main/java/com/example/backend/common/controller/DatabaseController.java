package com.example.backend.common.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DatabaseController {

    private final MongoTemplate mongoTemplate;

    @PostMapping("/reset-database")
    public ResponseEntity<String> resetDatabase() {
        // Drop all collections
        for (String collectionName : mongoTemplate.getCollectionNames()) {
            mongoTemplate.dropCollection(collectionName);
            log.info("Dropped collection: {}", collectionName);
        }

        // Re-import data
        log.info("Re-importing initial data...");
        importInitialData();
        log.info("Initial data imported successfully");

        return ResponseEntity.status(HttpStatus.OK).body("Database reset successfully");
    }

    private void importInitialData() {
        String[] collections = {
                "buyer_profiles", "conversionRate", "discountRates", "giftCards",
                "items", "seller_profiles", "transactions", "users", "wallets"
        };

        for (String collection : collections) {
            String fileName = "shopping_platform." + collection + ".json";
            String command = String.format(
                    "mongoimport --uri=\"%s\" --collection=%s --file=/data/%s --jsonArray",
                    System.getenv("SPRING_DATA_MONGODB_URI"),
                    collection,
                    fileName
            );

            try {
                Process process = Runtime.getRuntime().exec(new String[]{"sh", "-c", command});
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                int exitCode = process.waitFor();
                System.out.println("Exited with error code : " + exitCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}