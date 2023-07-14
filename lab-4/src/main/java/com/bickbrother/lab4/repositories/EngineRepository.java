package com.bickbrother.lab4.repositories;

import com.bickbrother.lab4.entities.Car;
import com.bickbrother.lab4.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngineRepository extends JpaRepository<Engine, Long> {
    List<Engine> getAllByCar_Id(Long carId);
    List<Engine> getAllByCarIn(List<Car> cars);
}
