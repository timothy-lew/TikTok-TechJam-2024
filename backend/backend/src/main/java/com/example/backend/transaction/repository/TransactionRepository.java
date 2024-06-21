package com.example.backend.transaction.repository;

import com.example.backend.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByBuyerProfileId(String buyerProfileId);

    List<Transaction> findBySellerProfileId(String sellerProfileId);
}
