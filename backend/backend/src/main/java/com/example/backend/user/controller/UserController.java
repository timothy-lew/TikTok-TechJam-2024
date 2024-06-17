package com.example.backend.user.controller;

import com.example.backend.user.dto.UserDTO;
import com.example.backend.user.dto.UserResponseDTO;
import com.example.backend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles user registration, authentication, profile management and role assignments.
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        UserResponseDTO responseDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String userId) {
        UserResponseDTO responseDTO = userService.getUserById(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String userId, @RequestBody @Valid UserDTO userDTO) {
        UserResponseDTO responseDTO = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
