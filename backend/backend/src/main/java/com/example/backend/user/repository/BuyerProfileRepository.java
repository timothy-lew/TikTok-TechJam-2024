package com.example.backend.user.repository;

import com.example.backend.user.model.BuyerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerProfileRepository extends MongoRepository<BuyerProfile, String> {
    Optional<BuyerProfile> findByUserId(String userId);
}
