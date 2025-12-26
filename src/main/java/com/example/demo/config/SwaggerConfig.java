package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean("srsOpenAPI") // Unique name to avoid conflict with OpenApiConfig
    @Primary            // Forces Spring to use THIS configuration over the other one
    public OpenAPI srsOpenAPI() {
        return new OpenAPI()
                // 1. SRS Requirements (Title, Version)
                .info(new Info()
                        .title("Smart Invoice Categorization API")
                        .version("1.0")
                        .description("API for managing and categorizing invoices."))
                
                // 2. IMPORTANT: Include the URL from OpenApiConfig here
                // Since this bean is Primary, it must have the URL to work.
                .servers(List.of(
                        new Server().url("https://9287.408procr.amypo.ai/")
                ))

                // 3. Security Requirements
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}