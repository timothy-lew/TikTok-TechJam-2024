package com.example.backend.auth.service;

import com.example.backend.auth.dto.LoginRequest;
import com.example.backend.auth.dto.LoginResponse;
import com.example.backend.auth.jwt.JwtTokenProvider;
import com.example.backend.auth.model.UserPrincipal;
import com.example.backend.common.exception.InvalidPrincipalException;
import com.example.backend.user.dto.UserResponseDTO;
import com.example.backend.user.mapper.UserMapper;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest loginRequest) throws InvalidPrincipalException {
        try {
            Optional<User> userOptional = this.userRepository.findByUsername(loginRequest.getUsername());
            User user = userOptional.orElseThrow(() -> new InvalidPrincipalException("Empty or unknown login credentials."));

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                throw new InvalidPrincipalException("Invalid password.");
            }
            String accessToken = tokenProvider.generateAccessToken(user.getId());
            String refreshToken = tokenProvider.generateRefreshToken(user.getId());
            UserResponseDTO userResponseDTO = userMapper.fromUsertoUserResponseDTO(user);

            return new LoginResponse("Bearer", accessToken, refreshToken, userResponseDTO);

        } catch (AuthenticationException e) {
            // Handle authentication failure, rethrow as InvalidPrincipalException
            throw new InvalidPrincipalException("Invalid username or password");
        }
    }

    public LoginResponse refreshToken(String refreshToken) throws InvalidPrincipalException {
        if (tokenProvider.validateToken(refreshToken)) {
            String userId = tokenProvider.getUserIdFromJWT(refreshToken);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new InvalidPrincipalException("Invalid refresh token"));
            String newAccessToken = tokenProvider.generateAccessToken(userId);
            UserResponseDTO userResponseDTO = userMapper.fromUsertoUserResponseDTO(user);
            return new LoginResponse("Bearer", newAccessToken, refreshToken, userResponseDTO);
        } else {
            throw new InvalidPrincipalException("Invalid refresh token");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new UserPrincipal(user);
    }

    public UserDetails loadUserById(String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
        return new UserPrincipal(user);
    }
}
