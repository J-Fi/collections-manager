package com.janflpk.collectionsmanager.backend.domain.user.facade;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.domain.user.UserDto;
import com.janflpk.collectionsmanager.backend.mapper.UserMapper;
import com.janflpk.collectionsmanager.backend.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private UserMapper userMapper;

    public User saveUser(UserDto userDto) {
        return userDbService.saveUser(userMapper.mapToUser(userDto));
    }
}
