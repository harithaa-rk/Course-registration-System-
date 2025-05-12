package com.course.registration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.course.registration.repository")
@EnableTransactionManagement
public class JpaConfig {
    // Configuration will be picked up automatically
}
