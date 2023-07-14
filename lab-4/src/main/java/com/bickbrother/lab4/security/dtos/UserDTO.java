package com.bickbrother.lab4.security.dtos;

import com.bickbrother.lab4.security.models.Role;
import com.bickbrother.lab4.security.models.Status;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private Role role;

    private Status status;

    private Long brandId;
}
