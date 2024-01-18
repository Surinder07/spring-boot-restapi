package com.codeqube.springboot2.service;

import com.codeqube.springboot2.dto.UserDto;
import com.codeqube.springboot2.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUserById(Long id);
}
