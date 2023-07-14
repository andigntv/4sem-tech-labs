package com.bickbrother.lab3.repositories;

import com.bickbrother.lab3.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    List<Engine> getAllByCar_Id(Long carId);
}
