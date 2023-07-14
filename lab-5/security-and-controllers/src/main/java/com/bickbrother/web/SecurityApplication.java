package com.bickbrother.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@EntityScan(basePackages = {"com.bickbrother.data.entities", "com.bickbrother.web.security.entities"})
@EnableJpaRepositories(basePackages = {"com.bickbrother.web.security.repositories", "com.bickbrother.data.repositories"})
@ComponentScan(basePackages = {"com.bickbrother.web.security", "com.bickbrother.web.controllers", "com.bickbrother.web.rabbit", "com.bickbrother.utils.configurations", "com.bickbrother.messages.configurations"})

public class     SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
