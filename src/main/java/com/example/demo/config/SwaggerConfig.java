package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {

        return new OpenAPI()
                .info(new Info()
                        .title("API for Smart Invoice Categorization")
                        .description("REST API for intelligent invoice categorization using rule-based matching")
                        .version("1.0.0")
                )
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .components(
                        new io.swagger.v3.oas.models.Components()
                                .addSecuritySchemes("bearer-key",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
