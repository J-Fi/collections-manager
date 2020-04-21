package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.User;
import com.janflpk.collectionsmanager.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDbService.class);

    @Autowired
    private UserRepository userRepo;

    public User saveUser (User user) {
        return userRepo.save(user);
    }

    public void deleteUser(final Long id) {
        userRepo.deleteById(id);
    }

    public User findById(final Long id) {
        return userRepo.findById(id).orElse(new User());
    }

    public User findUserByEmail(String userEmail) {
        try {
            return userRepo.getUserByEmail(userEmail);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new User();
        }
    }
}
