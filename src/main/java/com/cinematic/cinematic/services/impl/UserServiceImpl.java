package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.services.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){this.userRepository = userRepository;}
    public List<User> retrieveAllUsers(){
        log.info("Start - retrieveAllUsers - args:none");
        val users = userRepository.findAll();
        log.info("End - retrieveAllUsers - out: {}", users);
        return users;
    }

    public  User retrieveUserById(Long id){
        log.info("Start - retrieveUserById - args: id: {}", id);
        val user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new NotFoundException("User con id "+ id + " non trovato");
        }
        log.info("End - retrieveUserById - out: {}", user);
        return user.get();
    }
}
