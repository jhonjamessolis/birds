package com.ufinet.birds.config;
import com.ufinet.birds.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class UserDetailsServiceConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public UserDetailsServiceConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return customUserDetailsService;
    }
}