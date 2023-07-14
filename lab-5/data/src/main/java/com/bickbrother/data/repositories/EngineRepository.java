package com.bickbrother.data.repositories;

import com.bickbrother.data.entities.Car;
import com.bickbrother.data.entities.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long> {
    List<Engine> getAllByCar_Id(Long carId);
    List<Engine> getAllByCarIn(List<Car> cars);
}
