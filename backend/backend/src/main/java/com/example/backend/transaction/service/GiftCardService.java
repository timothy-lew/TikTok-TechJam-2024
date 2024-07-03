package com.example.backend.transaction.service;


import com.example.backend.transaction.exception.AlreadyUsedGiftCardException;
import com.example.backend.transaction.exception.InvalidGiftCardException;
import com.example.backend.transaction.model.GiftCard;
import com.example.backend.transaction.repository.GiftCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GiftCardService {

    private final GiftCardRepository giftCardRepository;

    public GiftCard validateGiftCard(String code) {
        GiftCard giftCard = giftCardRepository.findByCode(code)
                .orElseThrow(() -> new InvalidGiftCardException("Invalid gift card"));

        if (giftCard.isUsed()) {
            throw new AlreadyUsedGiftCardException("Gift card already used");
        }

        return giftCard;
    }

    public void markGiftCardAsUsed(GiftCard giftCard) {
        giftCard.setUsed(true);
        giftCardRepository.save(giftCard);
    }

    public GiftCard createGiftCard(String code, float value) {
        GiftCard giftCard = new GiftCard();
        giftCard.setCode(code);
        giftCard.setValue(value);
        giftCard.setUsed(false);
        return giftCardRepository.save(giftCard);
    }

    public List<GiftCard> getAllGiftCards() {
        return giftCardRepository.findAll();
    }
}
