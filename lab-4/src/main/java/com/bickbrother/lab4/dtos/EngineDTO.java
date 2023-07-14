package com.bickbrother.lab4.dtos;

import lombok.Data;

@Data
public class EngineDTO {
    private Long id;

    private String name;

    private Integer volume;

    private Integer cylinders;

    private Integer height;

    private Long carId;
}
