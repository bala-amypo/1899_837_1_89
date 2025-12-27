package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary // This forces the app to use THIS connection instead of the broken MySQL one
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Use H2 Driver
        dataSource.setDriverClassName("org.h2.Driver");
        // H2 In-Memory URL
        dataSource.setUrl("jdbc:h2:mem:smart;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}