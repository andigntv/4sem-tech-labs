package com.bickbrother.lab3.services.implementations;

import com.bickbrother.lab3.dtos.BrandDTO;
import com.bickbrother.lab3.entities.Brand;
import com.bickbrother.lab3.repositories.BrandRepository;
import com.bickbrother.lab3.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandService implements CrudServiceInterface<BrandDTO> {
    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public BrandDTO save(BrandDTO brandDTO) { return convertToDto(brandRepository.save(convertToEntity(brandDTO))); }

    @Override
    public BrandDTO getById(Long id) { return convertToDto(brandRepository.findById(id).orElseGet(() -> brandRepository.getReferenceById(id))); }

    @Override
    public BrandDTO update(BrandDTO brandDTO) { return convertToDto(brandRepository.save(convertToEntity(brandDTO))); }

    @Override
    public void deleteById(Long id) { brandRepository.deleteById(id); }

    private BrandDTO convertToDto(Brand brand) { return modelMapper.map(brand, BrandDTO.class); }
    private Brand convertToEntity(BrandDTO brandDTO) { return modelMapper.map(brandDTO, Brand.class); }
}
