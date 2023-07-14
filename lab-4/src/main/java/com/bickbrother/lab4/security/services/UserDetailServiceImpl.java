package com.bickbrother.lab4.security.services;

import com.bickbrother.lab4.security.models.SecurityUser;
import com.bickbrother.lab4.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SecurityUser.fromUser(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
