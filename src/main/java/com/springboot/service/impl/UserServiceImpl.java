package com.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.Exception.ResourceNotFoundException;
import com.springboot.dto.UserDto;
import com.springboot.entity.User;
import com.springboot.repositories.UserRepo;
import com.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepo userRepo;
     
     @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User saveUser(UserDto userDto) {
        User user=UserDtoToUser(userDto);
        Optional<User> optional=userRepo.findByEmail(user.getEmail());
        if(optional.isPresent()){
            throw new ResourceNotFoundException("User already present ");
        }
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setPassword(user.getPassword());
        user.setRoleList(List.of("ROLL_USER"));
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        User user=userRepo.findById(id).orElseThrow();
        userRepo.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> user=userRepo.findAll();
        return user;
    }

    @Override
    public Optional<User> getUserById(String id) {
        User user=userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found with ID : "+id));
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2=userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not Found with ID : "+user.getUserId()));
        return Optional.ofNullable(userRepo.save(user2));
    }

    private User UserDtoToUser(UserDto userDto){
        User user=User.builder()
                      .name(userDto.getName())
                      .email(userDto.getEmail())
                      .password(userDto.getPassword())
                      .gender(userDto.getGender())
                      .phonenumber(userDto.getPhonenumber())
                      .address(userDto.getAddress())
                      .birthDate(userDto.getBirthDate())
                      .country(userDto.getCountry())
                      .build();
        return user;
        
    }

   

}
