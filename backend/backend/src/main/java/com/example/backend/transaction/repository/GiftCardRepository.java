package com.example.backend.transaction.repository;

import com.example.backend.transaction.model.GiftCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GiftCardRepository extends MongoRepository<GiftCard, String> {
    Optional<GiftCard> findByCode(String code);
}
