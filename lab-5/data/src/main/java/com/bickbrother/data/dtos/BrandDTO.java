package com.bickbrother.data.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@JsonSerialize
@NoArgsConstructor
public class BrandDTO {
    private Long id;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private Date date;

    @JsonCreator
    public BrandDTO(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("date") Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
