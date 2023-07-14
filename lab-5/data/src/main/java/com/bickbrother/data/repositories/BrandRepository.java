package com.bickbrother.data.repositories;

import com.bickbrother.data.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> getAllByName(String name);
}
