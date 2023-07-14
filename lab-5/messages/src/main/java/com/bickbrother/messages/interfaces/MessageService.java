package com.bickbrother.messages.interfaces;

public interface MessageService<TDto> {
    String OPERATION_KEY = "operation";
    String SAVE_HEADER = "save";
    String GET_HEADER = "get";
    String UPDATE_HEADER = "update";
    String DELETE_HEADER = "delete";
    TDto save(TDto dto);
    TDto getById(Long id);
    TDto update(TDto dto);
    void deleteById(Long id);
}
