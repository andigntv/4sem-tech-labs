package com.bickbrother.lab3.repositories;

import com.bickbrother.lab3.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> getAllByBrand_Id(Long brandId);
    List<Car> getAllByName(String name);
}
