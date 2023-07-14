package com.bickbrother.data.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSerialize
@NoArgsConstructor
public class EngineDTO {
    private Long id;

    private String name;

    private Integer volume;

    private Integer cylinders;

    private Integer height;

    private Long carId;

    @JsonCreator
    public EngineDTO(@JsonProperty("id") Long id,
                     @JsonProperty("name") String name,
                     @JsonProperty("volume") Integer volume,
                     @JsonProperty("cylinders") Integer cylinders,
                     @JsonProperty("height") Integer height,
                     @JsonProperty("car_id") Long carId) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.cylinders = cylinders;
        this.height = height;
        this.carId = carId;
    }
}
