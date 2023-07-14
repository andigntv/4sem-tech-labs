package com.bickbrother.cars.services;

import com.bickbrother.data.dtos.CarDTO;
import com.bickbrother.data.entities.Car;
import com.bickbrother.data.repositories.CarRepository;
import com.bickbrother.utils.interfaces.CrudServiceInterface;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = {"com.bickbrother.data.repositories", "com.bickbrother.data.mappers"})
public class CarService implements CrudServiceInterface<CarDTO> {
    private final CarRepository carRepository;

    private final DtoMapper<Car, CarDTO> modelMapper;

    @Override

    public CarDTO save(CarDTO CarDTO) { return convertToDto(carRepository.save(convertToEntity(CarDTO))); }

    @Override
    public CarDTO getById(Long id) { return convertToDto(carRepository.findById(id).orElseGet(() -> carRepository.getReferenceById(id))); }

    @Override
    public CarDTO update(CarDTO CarDTO) { return convertToDto(carRepository.save(convertToEntity(CarDTO))); }

    @Override
    public void deleteById(Long id) { carRepository.deleteById(id); }

    private CarDTO convertToDto(Car car) { return modelMapper.toDto(car); }
    private Car convertToEntity(CarDTO carDTO) { return modelMapper.toEntity(carDTO); }
}
