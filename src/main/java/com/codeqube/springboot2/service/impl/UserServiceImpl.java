package com.codeqube.springboot2.service.impl;

import com.codeqube.springboot2.dto.UserDto;
import com.codeqube.springboot2.entity.User;
import com.codeqube.springboot2.repository.UserRepository;
import com.codeqube.springboot2.service.UserService;
import com.codeqube.springboot2.exception.ResourceNotFoundException;
import com.codeqube.springboot2.exception.UserEmailAlreadyExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> email = userRepository.findUserByEmail(userDto.getEmail());
        if(email.isPresent()){
            throw new UserEmailAlreadyExistException("Email already exist");
        }

        //convert userDto into JPA entity
       // User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepository.save(user);

        // convert user jpa entity to dto to return back
        //UserDto userDto1 = UserMapper.mapToUserDto(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public  UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("user", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));

        userRepository.deleteById(userId);
    }
}
