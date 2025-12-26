package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SmartInvoiceApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // This test ensures that the Spring Boot application context loads successfully.
        // If this passes, it means your SwaggerConfig, SecurityConfig, and Database connections are correct.
        assertThat(applicationContext).isNotNull();
    }
}