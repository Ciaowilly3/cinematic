package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.User;

import java.util.List;

public interface UserService {

    public List<User> retrieveAllUsers();

    public  User retrieveUserById(Long id);
}
