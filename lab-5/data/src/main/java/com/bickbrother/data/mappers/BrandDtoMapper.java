package com.bickbrother.data.mappers;

import com.bickbrother.data.entities.Brand;
import com.bickbrother.data.dtos.BrandDTO;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BrandDtoMapper implements DtoMapper<Brand, BrandDTO> {

    private final ModelMapper modelMapper;

    @Override
    public BrandDTO toDto(Brand brand) { return modelMapper.map(brand, BrandDTO.class); }

    @Override
    public Brand toEntity(BrandDTO brandDTO) { return modelMapper.map(brandDTO, Brand.class); }
}
