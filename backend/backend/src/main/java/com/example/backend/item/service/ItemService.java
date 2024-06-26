package com.example.backend.item.service;

import com.example.backend.common.validation.CommonValidationAndGetService;
import com.example.backend.item.dto.ItemDTO;
import com.example.backend.item.dto.ItemResponseDTO;
import com.example.backend.item.mapper.ItemMapper;
import com.example.backend.item.model.Item;
import com.example.backend.item.repository.ItemRepository;
import com.example.backend.user.model.SellerProfile;
import com.example.backend.user.model.User;
import com.example.backend.wallet.model.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CommonValidationAndGetService commonValidationAndGetService;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper, CommonValidationAndGetService commonValidationAndGetService) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.commonValidationAndGetService = commonValidationAndGetService;
    }

    public ItemResponseDTO createItem(ItemDTO itemDTO, String userId) {
        User user = commonValidationAndGetService.validateAndGetUser(userId);
        SellerProfile sellerProfile = user.getSellerProfile();
        Wallet sellerWallet = user.getWallet();

        Item item = itemMapper.fromItemDTOtoItem(itemDTO);
        item.setSellerProfileId(sellerProfile.getId());
        item.setBusinessName(sellerProfile.getBusinessName());
        item.setSellerWalletAddress(sellerWallet.getWalletAddress());
        Item savedItem = itemRepository.save(item);
        return itemMapper.fromItemtoItemResponseDTO(savedItem);
    }

    public List<ItemResponseDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(itemMapper::fromItemtoItemResponseDTO)
                .toList();
    }

    public ItemResponseDTO getItemById(String itemId) {
        Item existingItem = commonValidationAndGetService.validateAndGetItem(itemId);
        return itemMapper.fromItemtoItemResponseDTO(existingItem);
    }

    public List<ItemResponseDTO> getItemsByUserId(String userId) {
        SellerProfile sellerProfile = commonValidationAndGetService.validateAndGetSellerProfile(userId);
        List<Item> items = itemRepository.findBySellerProfileId(sellerProfile.getId());
        return items.stream()
                .map(itemMapper::fromItemtoItemResponseDTO)
                .toList();
    }

    public ItemResponseDTO updateItem(String itemId, ItemDTO itemDTO) throws IOException {
        Item existingItem = commonValidationAndGetService.validateAndGetItem(itemId);
        existingItem.setName(itemDTO.getName());
        existingItem.setDescription(itemDTO.getDescription());
        existingItem.setPrice(itemDTO.getPrice());
        existingItem.setQuantity(itemDTO.getQuantity());
        existingItem.setImage(itemMapper.multipartFileToBinary(itemDTO.getImage()));
        Item updatedItem = itemRepository.save(existingItem);
        return itemMapper.fromItemtoItemResponseDTO(updatedItem);
    }

    public void deleteItem(String itemId) {
        Item existingItem = commonValidationAndGetService.validateAndGetItem(itemId);
        itemRepository.delete(existingItem);
        // TODO: Ensure transaction persist even after deleting item.
    }

}
