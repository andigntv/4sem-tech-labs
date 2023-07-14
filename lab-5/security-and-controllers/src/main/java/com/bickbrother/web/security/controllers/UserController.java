package com.bickbrother.web.security.controllers;

import com.bickbrother.web.security.dtos.UserDTO;
import com.bickbrother.utils.interfaces.CrudServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('write')")
public class UserController {
    private final CrudServiceInterface<UserDTO> userService;

    @PostMapping("/")
    public UserDTO save(@RequestBody UserDTO userDTO) { return userService.save(userDTO); }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") Long id) { return userService.getById(id); }

    @PutMapping("/")
    public UserDTO update(@RequestBody UserDTO userDTO) { return userService.update(userDTO); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) { userService.deleteById(id);}
}
