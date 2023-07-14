package com.bickbrother.lab4.repositories;

import com.bickbrother.lab4.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> getAllByName(String name);
}
