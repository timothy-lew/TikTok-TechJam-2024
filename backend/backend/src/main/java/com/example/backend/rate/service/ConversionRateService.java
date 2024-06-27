package com.example.backend.rate.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.rate.model.ConversionRate;
import com.example.backend.rate.repository.ConversionRateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversionRateService {

    private final CommonValidationAndGetService commonValidationAndGetService;
    private final ConversionRateRepository conversionRateRepository;

    public ConversionRateService(CommonValidationAndGetService commonValidationAndGetService, ConversionRateRepository conversionRateRepository) {
        this.commonValidationAndGetService = commonValidationAndGetService;
        this.conversionRateRepository = conversionRateRepository;
    }

    public ConversionRate getCurrentRate() {
        return commonValidationAndGetService.validateAndGetCurrentConversionRate();
    }

    public ConversionRate setRate(float rate) {
        if (rate == 0) {
            throw new RuntimeException("Conversion rate cannot be 0");
        }
        ConversionRate conversionRate = new ConversionRate();
        conversionRate.setRate(rate);
        conversionRate.setInverseRate(1 / rate);
        conversionRate.setTimestamp(LocalDateTime.now()); // Set the timestamp
        return conversionRateRepository.save(conversionRate);
    }
}

