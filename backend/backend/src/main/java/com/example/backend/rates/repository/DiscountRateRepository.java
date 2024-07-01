package com.example.backend.rates.repository;

import com.example.backend.rates.model.DiscountRate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRateRepository extends MongoRepository<DiscountRate, String> {
    Optional<DiscountRate> findBySellerProfileId(String sellerProfileId);

    Optional<DiscountRate> findBySellerProfileIdIsNull(); // For global discount

    void deleteBySellerProfileId(String sellerProfileId);

    void deleteBySellerProfileIdIsNull();
}