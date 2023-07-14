package com.bickbrother.data.mappers;

import com.bickbrother.data.entities.Car;
import com.bickbrother.data.dtos.CarDTO;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarDtoMapper implements DtoMapper<Car, CarDTO> {

    private final ModelMapper modelMapper;

    @Override
    public CarDTO toDto(Car car) { return modelMapper.map(car, CarDTO.class); }

    @Override
    public Car toEntity(CarDTO carDTO) { return modelMapper.map(carDTO, Car.class); }
}
