package com.codeqube.springboot2.mapper;

import com.codeqube.springboot2.dto.UserDto;
import com.codeqube.springboot2.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {

        //convert userDto in JPA entity
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
