package com.bickbrother.data.mappers;

import com.bickbrother.data.dtos.EngineDTO;
import com.bickbrother.data.entities.Engine;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EngineDtoMapper implements DtoMapper<Engine, EngineDTO> {

    private final ModelMapper modelMapper;

    @Override
    public EngineDTO toDto(Engine engine) { return modelMapper.map(engine, EngineDTO.class); }

    @Override
    public Engine toEntity(EngineDTO engineDTO) { return modelMapper.map(engineDTO, Engine.class); }
}
