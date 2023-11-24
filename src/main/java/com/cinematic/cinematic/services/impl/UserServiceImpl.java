package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateUserRequestDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.CinemaRepository;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CinemaRepository cinemaRepository;
    public List<User> retrieveAllUsers(){
        log.info("Start - retrieveAllUsers - args:none");
        val users = userRepository.findAll();
        log.info("End - retrieveAllUsers - out: {}", users.size());
        return users;
    }

    public  User retrieveUserById(Long id){
        log.info("Start - retrieveUserById - args: id: {}", id);
        val user = userRepository.findById(id);
        log.info("End - retrieveUserById - out: {}", user.orElse(null));
        return user.orElseThrow(() -> new NotFoundException("User with id "+ id + " not found"));
    }

    public void makeUser(CreateUserRequestDto createUserRequestDto) {
        log.info("Start - makeUser - args: userRequest: {}", createUserRequestDto);
        Optional<Cinema> cinema = Optional.empty();
        if (createUserRequestDto.getCinemaId() != null) {
            cinema = cinemaRepository.findById(createUserRequestDto.getCinemaId());
        }
        val user = User.builder()
                .userName(createUserRequestDto.getUserName())
                .role(createUserRequestDto.getRole())
                .email(createUserRequestDto.getEmail())
                .password(createUserRequestDto.getPassword())
                .cinema(cinema.orElse(null))
                .build();
        userRepository.save(user);
        log.info("End - makeUser - out: {}", user);
    }
}
