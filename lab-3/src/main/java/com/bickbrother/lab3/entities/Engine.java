package com.bickbrother.lab3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer volume;

    private Integer cylinders;

    private Integer height;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;
}
