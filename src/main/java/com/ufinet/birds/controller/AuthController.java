package com.ufinet.birds.controller;

import com.ufinet.birds.entity.User;
import com.ufinet.birds.model.AuthRequestDTO;
import com.ufinet.birds.model.AuthResponseDTO;
import com.ufinet.birds.model.RegisterUserRequestDTO;
import com.ufinet.birds.service.AuthenticationService;
import com.ufinet.birds.service.CustomUserDetailsService;
import com.ufinet.birds.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequestDTO registerUserRequest) {
        User user = customUserDetailsService.register(registerUserRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
        try {
            AuthResponseDTO response = authenticationService.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}