package com.bavilivre.bavilivre_backend;

import com.bavilivre.bavilivre_backend.infra.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BavilivreBackendApplicationTests {

    // Check that the Spring context is loading
    @Test
    void contextLoads() {
    }

    // Simple test: checks that SecurityConfig is loaded
    @Autowired
    private SecurityConfig securityConfig;

    @Test
    void securityConfigLoads() {
        assertThat(securityConfig).isNotNull();
    }
}