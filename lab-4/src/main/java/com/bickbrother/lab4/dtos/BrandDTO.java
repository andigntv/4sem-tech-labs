package com.bickbrother.lab4.dtos;

import lombok.Data;
import java.sql.Date;

@Data
public class BrandDTO {
    private Long id;

    private String name;

    private Date date;
}
