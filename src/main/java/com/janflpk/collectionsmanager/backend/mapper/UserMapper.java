package com.janflpk.collectionsmanager.backend.mapper;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.domain.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword());
    }
}
