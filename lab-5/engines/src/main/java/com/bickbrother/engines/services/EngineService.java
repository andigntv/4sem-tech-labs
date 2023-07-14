package com.bickbrother.engines.services;

import com.bickbrother.data.dtos.EngineDTO;
import com.bickbrother.data.entities.Engine;
import com.bickbrother.data.repositories.EngineRepository;
import com.bickbrother.utils.interfaces.CrudServiceInterface;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = {"com.bickbrother.data.repositories", "com.bickbrother.data.mappers"})
public class EngineService implements CrudServiceInterface<EngineDTO> {
    private final EngineRepository engineRepository;

    private final DtoMapper<Engine, EngineDTO> modelMapper;

    @Override
    public EngineDTO save(EngineDTO engineDTO) { return convertToDto(engineRepository.save(convertToEntity(engineDTO))); }

    @Override
    public EngineDTO getById(Long id) { return convertToDto(engineRepository.findById(id).orElseGet(() -> engineRepository.getReferenceById(id))); }

    @Override
    public EngineDTO update(EngineDTO engineDTO) { return convertToDto(engineRepository.save(convertToEntity(engineDTO))); }

    @Override
    public void deleteById(Long id) { engineRepository.deleteById(id); }

    private EngineDTO convertToDto(Engine engine) { return modelMapper.toDto(engine); }
    private Engine convertToEntity(EngineDTO engineDTO) { return modelMapper.toEntity(engineDTO); }
}