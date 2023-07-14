package com.bickbrother.lab3.services.interfaces;

public interface CrudServiceInterface<T>{
    T save(T entity);
    T getById(Long id);
    T update(T entity);
    void deleteById(Long id);

}
