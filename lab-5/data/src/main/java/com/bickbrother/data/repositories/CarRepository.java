package com.bickbrother.data.repositories;

import com.bickbrother.data.entities.Brand;
import com.bickbrother.data.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> getAllByBrand(Brand brand);
    List<Car> getAllByBrand_Id(Long brandId);
    List<Car> getAllByName(String name);
}
