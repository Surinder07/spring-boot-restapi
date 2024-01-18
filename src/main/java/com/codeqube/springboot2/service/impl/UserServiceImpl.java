package com.codeqube.springboot2.service.impl;

import com.codeqube.springboot2.dto.UserDto;
import com.codeqube.springboot2.entity.User;
import com.codeqube.springboot2.mapper.UserMapper;
import com.codeqube.springboot2.repository.UserRepository;
import com.codeqube.springboot2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        //convert userDto into JPA entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);


        // convert user jpa entity to dto to return back
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public  UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
