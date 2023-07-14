package com.bickbrother.lab4.security.services;


import com.bickbrother.lab4.security.dtos.UserDTO;
import com.bickbrother.lab4.security.models.User;
import com.bickbrother.lab4.security.repositories.UserRepository;
import com.bickbrother.lab4.services.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements CrudServiceInterface<UserDTO> {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserDTO save(UserDTO userDTO) { return convertToDto(userRepository.save(convertToEntity(userDTO))); }

    @Override
    public UserDTO getById(Long id) { return convertToDto(userRepository.findById(id).orElseGet(() -> userRepository.getReferenceById(id))); }

    @Override
    public UserDTO update(UserDTO userDTO) { return convertToDto(userRepository.save(convertToEntity(userDTO))); }

    @Override
    public void deleteById(Long id) { userRepository.deleteById(id); }

    private UserDTO convertToDto(User user) { return modelMapper.map(user, UserDTO.class); }
    private User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }
}
