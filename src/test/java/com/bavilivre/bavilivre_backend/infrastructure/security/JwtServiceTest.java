package com.bavilivre.bavilivre_backend.infrastructure.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void shouldGenerateTokenAndExtractEmail() {

        String email = "david@bavilivre.dev";

        String token = jwtService.generateToken(email);

        String extractedEmail = jwtService.extractEmail(token);

        assertThat(extractedEmail).isEqualTo(email);
    }

    @Test
    void shouldValidateGeneratedToken() {

        String token = jwtService.generateToken("david@bavilivre.dev");

        assertThat(jwtService.isTokenValid(token)).isTrue();
    }

}