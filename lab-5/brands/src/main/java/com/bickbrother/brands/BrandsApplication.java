package com.bickbrother.brands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.bickbrother.data.entities"})
@EnableJpaRepositories(basePackages = {"com.bickbrother.data.repositories"})
@ComponentScan(basePackages = {"com.bickbrother.brands.rabbit", "com.bickbrother.brands.services", "com.bickbrother.utils.configurations", "com.bickbrother.messages.configurations"})
public class BrandsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrandsApplication.class, args);
    }

}
