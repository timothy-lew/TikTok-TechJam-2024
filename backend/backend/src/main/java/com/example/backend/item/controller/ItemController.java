package com.example.backend.item.controller;

import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.controller.BaseController;
import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.item.dto.ItemDTO;
import com.example.backend.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController extends BaseController {

    private final ItemService itemService;
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createItem(@ModelAttribute @Valid ItemDTO itemDTO, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userNotSeller(userPrincipal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not a seller");
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDTO, userPrincipal.getId()));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error creating item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getAllItems());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.info("Error fetching all items", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable String itemId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemById(itemId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching item with id: {}", itemId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getItemsByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemsByUserId(userId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching items for userId: {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/seller/{sellerProfileId}")
    public ResponseEntity<?> getItemsBySellerProfileId(@PathVariable String sellerProfileId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemsBySellerProfileId(sellerProfileId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error fetching items for sellerProfileId: {}", sellerProfileId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PutMapping(value = "/{itemId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateItem(@PathVariable String itemId, @ModelAttribute @Valid ItemDTO itemDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemService.updateItem(itemId, itemDTO));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            log.error("Error processing image for item: {}", itemId, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to process item image: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error updating item with id: {}", itemId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable String itemId) {
        try {
            itemService.deleteItem(itemId);
            return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting item with id: {}", itemId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}
