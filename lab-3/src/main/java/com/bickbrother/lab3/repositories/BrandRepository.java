package com.bickbrother.lab3.repositories;

import com.bickbrother.lab3.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> getAllByName(String name);
}
