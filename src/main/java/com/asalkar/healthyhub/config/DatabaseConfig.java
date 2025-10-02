package com.asalkar.healthyhub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.asalkar.healthyhub.repository")
@EnableTransactionManagement
public class DatabaseConfig {
}