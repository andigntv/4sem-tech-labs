package com.bickbrother.web.security.entities;

import com.bickbrother.data.entities.Brand;
import com.bickbrother.web.security.models.Role;
import com.bickbrother.web.security.models.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.EAGER)
    private Brand brand;
}