package com.bickbrother.lab4.dtos;

import com.bickbrother.lab4.models.database.Body;
import lombok.Data;

@Data
public class CarDTO {
    private Long id;

    private String name;

    private Integer length;

    private Integer width;

    private Integer height;

    private Body body;

    private Long brandId;
}
