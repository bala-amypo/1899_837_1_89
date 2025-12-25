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

    @Bean
    @Primary // This ensures this configuration wins if there are duplicates
    public OpenAPI myProjectOpenAPI() { // Renamed from 'customOpenAPI' to avoid conflict
        return new OpenAPI()
                // 1. SRS Requirements (Title, Desc, Version)
                .info(new Info()
                        .title("Smart Invoice Categorization API")
                        .version("1.0")
                        .description("API for managing and categorizing invoices."))
                
                // 2. Your Environment Server URL (From your OpenApiConfig)
                .servers(List.of(
                        new Server().url("https://9287.408procr.amypo.ai/")
                ))

                // 3. Security (JWT Bearer Token)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}