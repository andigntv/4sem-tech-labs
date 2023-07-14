package com.bickbrother.lab3.controllers;

import com.bickbrother.lab3.dtos.BrandDTO;
import com.bickbrother.lab3.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/brands")
public class BrandController {
    private final CrudServiceInterface<BrandDTO> brandService;

    @PostMapping("/")
    public BrandDTO save(@RequestBody BrandDTO brandDTO) { return brandService.save(brandDTO); }

    @GetMapping("/{id}")
    public BrandDTO getById(@PathVariable("id") Long id) { return brandService.getById(id); }

    @PutMapping("/")
    public BrandDTO update(@RequestBody BrandDTO brandDTO) { return brandService.update(brandDTO); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) { brandService.deleteById(id);}
}
