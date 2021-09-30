package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.repository.UserRepository;
import com.janflpk.collectionsmanager.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        User user = userRepository.getUserByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException(userEmail);
        }
        return new UserDetailsImpl(user);
    }
}
