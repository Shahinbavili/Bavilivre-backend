package com.bavilivre.bavilivre_backend.infrastructure.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    @Test
    void shouldHashAndVerifyPasswordWithBcrypt() {
        var passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "password";
        String hash = passwordEncoder.encode(rawPassword);

        assertThat(hash).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, hash)).isTrue();
        assertThat(passwordEncoder.matches("wrong-password", hash)).isFalse();
    }
}
