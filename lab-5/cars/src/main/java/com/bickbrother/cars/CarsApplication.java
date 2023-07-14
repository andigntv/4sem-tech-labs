package com.bickbrother.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.bickbrother.data.entities"})
@EnableJpaRepositories(basePackages = {"com.bickbrother.data.repositories"})
@ComponentScan(basePackages = {"com.bickbrother.cars.rabbit", "com.bickbrother.cars.services", "com.bickbrother.utils.configurations", "com.bickbrother.messages.configurations"})
public class CarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }

}
