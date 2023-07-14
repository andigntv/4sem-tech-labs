package com.bickbrother.data.dtos;

import com.bickbrother.data.models.Body;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSerialize
@NoArgsConstructor
public class CarDTO {
    private Long id;

    private String name;

    private Integer length;

    private Integer width;

    private Integer height;

    private Body body;

    private Long brandId;

    @JsonCreator
    public CarDTO(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("length") Integer length,
                    @JsonProperty("width") Integer width,
                    @JsonProperty("height") Integer height,
                    @JsonProperty("body") Body body,
                    @JsonProperty("brand_id") Long brandId ) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.body = body;
        this.brandId = brandId;

    }
}
