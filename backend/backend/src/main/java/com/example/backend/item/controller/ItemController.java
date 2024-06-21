package com.example.backend.item.controller;

import com.example.backend.item.dto.ItemDTO;
import com.example.backend.item.dto.ItemResponseDTO;
import com.example.backend.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping(value = "{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ItemResponseDTO> createItem(@PathVariable String userId, @ModelAttribute @Valid ItemDTO itemDTO) {
        ItemResponseDTO responseDTO = itemService.createItem(itemDTO, userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> getItemById(@PathVariable String itemId) {
        ItemResponseDTO responseDTO = itemService.getItemById(itemId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ItemResponseDTO>> getItemsByUserId(@PathVariable String userId) {
        List<ItemResponseDTO> responseDTOs = itemService.getItemsByUserId(userId);
        return ResponseEntity.ok(responseDTOs);
    }

    @PutMapping(value = "/{itemId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable String itemId, @ModelAttribute @Valid ItemDTO itemDTO) {
        ItemResponseDTO responseDTO;
        try {
            responseDTO = itemService.updateItem(itemId, itemDTO);
        } catch (IOException e) {
            log.error("Error updating item with id: {}", itemId);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable String itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }

}
