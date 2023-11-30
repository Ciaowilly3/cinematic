package com.cinematic.cinematic.security;

import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws NotFoundException{
        val user = userRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("user with name" + userName + "not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );

    }
}
