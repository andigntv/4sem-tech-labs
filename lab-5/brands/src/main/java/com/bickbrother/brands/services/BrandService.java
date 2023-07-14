package com.bickbrother.brands.services;

import com.bickbrother.data.dtos.BrandDTO;
import com.bickbrother.data.entities.Brand;
import com.bickbrother.data.repositories.BrandRepository;
import com.bickbrother.utils.interfaces.CrudServiceInterface;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@ComponentScan(basePackages = {"com.bickbrother.data.repositories", "com.bickbrother.data.mappers"})
public class BrandService implements CrudServiceInterface<BrandDTO> {
    private final BrandRepository brandRepository;

    private final DtoMapper<Brand, BrandDTO> modelMapper;

    @Override
    public BrandDTO save(BrandDTO brandDTO) { return convertToDto(brandRepository.save(convertToEntity(brandDTO))); }

    @Override
    public BrandDTO getById(Long id) { return convertToDto(brandRepository.findById(id).orElseGet(() -> brandRepository.getReferenceById(id))); }

    @Override
    public BrandDTO update(BrandDTO brandDTO) { return convertToDto(brandRepository.save(convertToEntity(brandDTO))); }

    @Override
    public void deleteById(Long id) { brandRepository.deleteById(id); }

    private BrandDTO convertToDto(Brand brand) { return modelMapper.toDto(brand); }
    private Brand convertToEntity(BrandDTO brandDTO) { return modelMapper.toEntity(brandDTO); }
}
