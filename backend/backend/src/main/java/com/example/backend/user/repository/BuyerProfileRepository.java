package com.example.backend.user.repository;

import com.example.backend.user.model.BuyerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerProfileRepository extends MongoRepository<BuyerProfile, String> {
}
