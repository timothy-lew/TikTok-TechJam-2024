package com.example.backend.rates.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.rates.dto.DiscountRateDTO;
import com.example.backend.rates.dto.DiscountRateResponseDTO;
import com.example.backend.rates.mapper.DiscountRateMapper;
import com.example.backend.rates.model.ConversionRate;
import com.example.backend.rates.model.DiscountRate;
import com.example.backend.rates.repository.ConversionRateRepository;
import com.example.backend.rates.repository.DiscountRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatesService {

    private final CommonValidationAndGetService commonValidationAndGetService;
    private final ConversionRateRepository conversionRateRepository;
    private final ItemRepository itemRepository;
    private final DiscountRateRepository discountRateRepository;
    private final DiscountRateMapper discountRateMapper;

    public RatesService(CommonValidationAndGetService commonValidationAndGetService, ConversionRateRepository conversionRateRepository, ItemRepository itemRepository, DiscountRateRepository discountRateRepository, DiscountRateMapper discountRateMapper) {
        this.commonValidationAndGetService = commonValidationAndGetService;
        this.conversionRateRepository = conversionRateRepository;
        this.itemRepository = itemRepository;
        this.discountRateRepository = discountRateRepository;
        this.discountRateMapper = discountRateMapper;
    }

    public ConversionRate getCurrentConversionRate() {
        return commonValidationAndGetService.validateAndGetCurrentConversionRate();
    }

    public ConversionRate setConversionRate(float rate) {
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

    public List<ConversionRate> getAllConversionRates() {
        return conversionRateRepository.findAll();
    }

    @Transactional
    public DiscountRateResponseDTO setDiscountRate(DiscountRateDTO discountRateDTO) {
        DiscountRate discountRate = discountRateMapper.fromDiscountRateDTOtoDiscountRate(discountRateDTO);
        discountRate.setTimestamp(LocalDateTime.now());

        DiscountRate savedRate = discountRateRepository.save(discountRate);

        // Apply discount to items
        applyDiscountToItems(savedRate);

        return discountRateMapper.fromDiscountRatetoDiscountRateResponseDTO(savedRate);
    }

    private void applyDiscountToItems(DiscountRate discountRate) {
        List<Item> items = discountRate.getSellerProfileId() == null ?
                itemRepository.findAll() :
                itemRepository.findBySellerProfileId(discountRate.getSellerProfileId());

        for (Item item : items) {
            applyDiscountToItem(item, discountRate);
            itemRepository.save(item);
        }
    }

    private void applyDiscountToItem(Item item, DiscountRate discountRate) {
        item.setDiscountRate(discountRate);
        if (discountRate.getApplyTo().contains(DiscountRate.ApplyTo.PRICE)) {
            item.setDiscountedPrice(calculateDiscountedPrice(item.getPrice(), discountRate.getRate()));
        } else {
            item.setDiscountedPrice(null);
        }
        if (discountRate.getApplyTo().contains(DiscountRate.ApplyTo.TOKTOKEN_PRICE)) {
            item.setDiscountedTokTokenPrice(calculateDiscountedPrice(item.getTokTokenPrice(), discountRate.getRate()));
        } else {
            item.setDiscountedTokTokenPrice(null);
        }
    }

    private Float calculateDiscountedPrice(Float originalPrice, float discountRate) {
        return originalPrice * (1 - discountRate / 100);
    }

    public DiscountRateResponseDTO getDiscountRate(String sellerProfileId) {
        DiscountRate discountRate = sellerProfileId == null ?
                discountRateRepository.findBySellerProfileIdIsNull().orElse(null) :
                discountRateRepository.findBySellerProfileId(sellerProfileId).orElse(null);

        return discountRate != null ? discountRateMapper.fromDiscountRatetoDiscountRateResponseDTO(discountRate) : null;
    }

    public List<DiscountRateResponseDTO> getAllDiscountRates() {
        List<DiscountRate> discountRates = discountRateRepository.findAll();
        return discountRateMapper.fromDiscountRateListtoDiscountRateResponseDTOList(discountRates);
    }

    @Transactional
    public void removeDiscount(String sellerProfileId) {
        List<Item> items;
        if (sellerProfileId == null) {
            // Remove global discount
            discountRateRepository.deleteBySellerProfileIdIsNull();
            items = itemRepository.findAll();
        } else {
            // Remove seller-specific discount
            discountRateRepository.deleteBySellerProfileId(sellerProfileId);
            items = itemRepository.findBySellerProfileId(sellerProfileId);
        }

        for (Item item : items) {
            removeDiscountFromItem(item);
            itemRepository.save(item);
        }
    }

    private void removeDiscountFromItem(Item item) {
        item.setDiscountRate(null);
        item.setDiscountedPrice(null);
        item.setDiscountedTokTokenPrice(null);
    }
}

