package com.ufinet.birds.service;

import com.ufinet.birds.model.AuthRequestDTO;
import com.ufinet.birds.model.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());

            if (user.getUsername().equals(request.getEmail()) && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String jwtToken = jwtService.generateToken(user);
                return new AuthResponseDTO(jwtToken);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", request.getEmail(), e);
            throw new RuntimeException("Invalid credentials");
        }
    }
}