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

    // Unique Name "srsOpenApiBean" avoids conflict with "customOpenAPI"
    @Bean("srsOpenApiBean") 
    @Primary
    public OpenAPI srsOpenApiBean() { 
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Invoice Categorization API")
                        .version("1.0")
                        .description("API for managing and categorizing invoices."))
                // Include your environment URL
                .servers(List.of(
                        new Server().url("https://9287.408procr.amypo.ai/")
                ))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}