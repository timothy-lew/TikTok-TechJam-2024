package com.example.backend.rate.repository;

import com.example.backend.rate.model.ConversionRate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionRateRepository extends MongoRepository<ConversionRate, String> {
    @NotNull
    @Query("{}")
    List<ConversionRate> findAll(@NotNull Sort sort);
}
