package com.example.backend.rate.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.rate.model.ConversionRate;
import com.example.backend.rate.repository.ConversionRateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConversionRateService {

    private final CommonValidationAndGetService commonValidationAndGetService;
    private final ConversionRateRepository conversionRateRepository;
    private final ItemRepository itemRepository;

    public ConversionRateService(CommonValidationAndGetService commonValidationAndGetService, ConversionRateRepository conversionRateRepository, ItemRepository itemRepository) {
        this.commonValidationAndGetService = commonValidationAndGetService;
        this.conversionRateRepository = conversionRateRepository;
        this.itemRepository = itemRepository;
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
        conversionRate.setTimestamp(LocalDateTime.now());

        // Save the new conversion rate
        ConversionRate savedRate = conversionRateRepository.save(conversionRate);

        // Update tokTokenPrice for all items
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            item.setTokTokenPrice(item.getPrice() * rate);
            itemRepository.save(item);
        }

        return savedRate;
    }

    public List<ConversionRate> getAllRates() {
        return conversionRateRepository.findAll();
    }
}

