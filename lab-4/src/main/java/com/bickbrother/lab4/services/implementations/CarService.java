package com.bickbrother.lab4.services.implementations;

import com.bickbrother.lab4.dtos.CarDTO;
import com.bickbrother.lab4.entities.Car;
import com.bickbrother.lab4.repositories.CarRepository;
import com.bickbrother.lab4.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService implements CrudServiceInterface<CarDTO> {
    private final CarRepository carRepository;

    private final ModelMapper modelMapper;

    public CarDTO save(CarDTO CarDTO) { return convertToDto(carRepository.save(convertToEntity(CarDTO))); }

    @Override
    public CarDTO getById(Long id) { return convertToDto(carRepository.findById(id).orElseGet(() -> carRepository.getReferenceById(id))); }

    @Override
    public CarDTO update(CarDTO CarDTO) { return convertToDto(carRepository.save(convertToEntity(CarDTO))); }

    @Override
    public void deleteById(Long id) { carRepository.deleteById(id); }

    private CarDTO convertToDto(Car car) { return modelMapper.map(car, CarDTO.class); }
    private Car convertToEntity(CarDTO carDTO) { return modelMapper.map(carDTO, Car.class); }
}
