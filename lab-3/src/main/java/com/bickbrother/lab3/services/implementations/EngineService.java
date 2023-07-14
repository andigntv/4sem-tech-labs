package com.bickbrother.lab3.services.implementations;

import com.bickbrother.lab3.dtos.EngineDTO;
import com.bickbrother.lab3.entities.Engine;
import com.bickbrother.lab3.repositories.EngineRepository;
import com.bickbrother.lab3.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EngineService implements CrudServiceInterface<EngineDTO> {
    private final EngineRepository engineRepository;

    private final ModelMapper modelMapper;

    public EngineDTO save(EngineDTO engineDTO) { return convertToDto(engineRepository.save(convertToEntity(engineDTO))); }

    @Override
    public EngineDTO getById(Long id) { return convertToDto(engineRepository.findById(id).orElseGet(() -> engineRepository.getReferenceById(id))); }

    @Override
    public EngineDTO update(EngineDTO engineDTO) { return convertToDto(engineRepository.save(convertToEntity(engineDTO))); }

    @Override
    public void deleteById(Long id) { engineRepository.deleteById(id); }

    private EngineDTO convertToDto(Engine engine) { return modelMapper.map(engine, EngineDTO.class); }
    private Engine convertToEntity(EngineDTO engineDTO) { return modelMapper.map(engineDTO, Engine.class); }
}