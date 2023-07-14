package com.bickbrother.web.security.dos;

import com.bickbrother.web.security.models.Role;
import com.bickbrother.web.security.entities.User;
import com.bickbrother.web.security.repositories.UserRepository;
import com.bickbrother.data.dtos.BrandDTO;
import com.bickbrother.data.dtos.CarDTO;
import com.bickbrother.data.dtos.EngineDTO;
import com.bickbrother.data.entities.Brand;
import com.bickbrother.data.entities.Car;
import com.bickbrother.data.entities.Engine;
import com.bickbrother.data.repositories.BrandRepository;
import com.bickbrother.data.repositories.CarRepository;
import com.bickbrother.data.repositories.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainSecurity {

    private final UserRepository userRepository;

    private final BrandRepository brandRepository;

    private final CarRepository carRepository;

    private final EngineRepository engineRepository;


    // EntityIsAllowed(username, EntityDto) methods are used to check if this user allowed to create entity

    public boolean brandIsAllowed(String username, BrandDTO brandDTO) {
        if (getUser(username).getRole().equals(Role.ADMIN))
            return true;
        return getBrand(username).getId().equals(brandDTO.getId());
    }

    public boolean carIsAllowed(String username, CarDTO carDTO) {
        if (getUser(username).getRole().equals(Role.ADMIN))
            return true;
        return brandIsAllowed(username, carDTO.getBrandId());
    }

    public boolean engineIsAllowed(String username, EngineDTO engineDTO) {
        if (getUser(username).getRole().equals(Role.ADMIN))
            return true;
        return carIsAllowed(username, engineDTO.getCarId());
    }

    public boolean brandIsAllowed(String username, Long brandId) {
        if (getUser(username).getRole().equals(Role.ADMIN))
            return true;
        return getBrand(username).getId().equals(brandId);
    }

    public boolean carIsAllowed(String username, Long carId) {
        if (getUser(username).getRole().equals(Role.ADMIN))
            return true;
        return getCars(username)
                .stream()
                .map(Car::getId)
                .toList()
                .contains(carId);
    }

    public boolean engineIsAllowed(String username, Long engineId) {
        if (getUser(username).getRole().equals(Role.ADMIN))
            return true;
        return getEngines(username)
                .stream()
                .map(Engine::getId)
                .toList()
                .contains(engineId);
    }
    public Brand getBrand(String username) {
        return brandRepository.findById(getUser(username).getBrand().getId()).orElseThrow();
    }

    public List<Car> getCars(String username) {
        return carRepository.getAllByBrand_Id(getBrand(username).getId());
    }

    public List<Engine> getEngines(String username) { return engineRepository.getAllByCarIn(getCars(username)); }
    private User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
