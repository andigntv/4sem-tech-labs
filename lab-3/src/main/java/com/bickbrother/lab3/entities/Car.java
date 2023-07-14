package com.bickbrother.lab3.entities;

import com.bickbrother.lab3.models.Body;
import lombok.*;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer length;

    private Integer width;

    private Integer height;

    @Enumerated(EnumType.STRING)
    private Body body;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;
}
