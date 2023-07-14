package com.bickbrother.lab4.repositories;

import com.bickbrother.lab4.entities.Brand;
import com.bickbrother.lab4.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> getAllByBrand(Brand brand);
    List<Car> getAllByBrand_Id(Long brandId);
    List<Car> getAllByName(String name);
}
