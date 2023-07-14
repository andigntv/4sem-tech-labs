package com.bickbrother.lab3.controllers;

import com.bickbrother.lab3.dtos.EngineDTO;
import com.bickbrother.lab3.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/engine")
public class EngineController {
    private final CrudServiceInterface<EngineDTO> engineService;

    @PostMapping("/")
    public EngineDTO save(@RequestBody EngineDTO engineDTO) { return engineService.save(engineDTO); }

    @GetMapping("/{id}")
    public EngineDTO getById(@PathVariable("id") Long id) { return engineService.getById(id); }

    @PutMapping("/")
    public EngineDTO update(@RequestBody EngineDTO engineDTO) { return engineService.update(engineDTO); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) { engineService.deleteById(id);}

}
