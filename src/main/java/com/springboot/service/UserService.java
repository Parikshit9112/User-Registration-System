package com.springboot.service;

import java.util.List;
import java.util.Optional;

import com.springboot.dto.UserDto;
import com.springboot.entity.User;

public interface UserService  {
    
    //create
    User saveUser(UserDto userDto);

    //get user
    Optional<User> getUserById(String id);
    List<User> getAllUsers();

    //update user
    Optional<User> updateUser(User user);

    //delete user
    void deleteUser(String id);

}
