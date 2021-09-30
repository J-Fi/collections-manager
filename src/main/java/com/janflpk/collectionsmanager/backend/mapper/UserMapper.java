package com.janflpk.collectionsmanager.backend.mapper;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.domain.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()));
    }
}
