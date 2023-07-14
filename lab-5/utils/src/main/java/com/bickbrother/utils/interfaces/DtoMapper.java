package com.bickbrother.utils.interfaces;

public interface DtoMapper<T, TDto> {
    TDto toDto(T entity);
    T toEntity(TDto dto);
}
