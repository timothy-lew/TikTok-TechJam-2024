package com.example.backend.user.repository;

import com.example.backend.user.model.SellerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerProfileRepository extends MongoRepository<SellerProfile, String> {
}
