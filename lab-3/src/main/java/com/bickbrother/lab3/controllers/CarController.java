package com.bickbrother.lab3.controllers;

import com.bickbrother.lab3.dtos.CarDTO;
import com.bickbrother.lab3.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CrudServiceInterface<CarDTO> carService;

    @PostMapping("/")
    public CarDTO save(@RequestBody CarDTO carDTO) { return carService.save(carDTO); }

    @GetMapping("/{id}")
    public CarDTO getById(@PathVariable("id") Long id) { return carService.getById(id); }

    @PutMapping("/")
    public CarDTO update(@RequestBody CarDTO carDTO) { return carService.update(carDTO); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) { carService.deleteById(id);}
}
