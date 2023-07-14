package com.bickbrother.web.security.mappers;

import com.bickbrother.web.security.dtos.UserDTO;
import com.bickbrother.web.security.entities.User;
import com.bickbrother.utils.interfaces.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDtoMapper implements DtoMapper<User, UserDTO> {
    private final ModelMapper modelMapper;

    @Override
    public UserDTO toDto(User user) { return modelMapper.map(user, UserDTO.class); }

    @Override
    public User toEntity(UserDTO userDTO) { return modelMapper.map(userDTO, User.class); }
}
