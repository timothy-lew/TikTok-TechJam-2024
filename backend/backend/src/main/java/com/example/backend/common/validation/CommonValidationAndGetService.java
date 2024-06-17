package com.example.backend.common.validation;

import com.example.backend.common.exception.ResourceNotFoundException;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CommonValidationAndGetService {

    private final UserRepository userRepository;

    public User validateAndGetUser(String userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }
}
