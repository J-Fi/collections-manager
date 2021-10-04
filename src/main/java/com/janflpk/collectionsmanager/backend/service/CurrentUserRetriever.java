package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserRetriever {

    @Autowired
    private UserDbService userDbService;

    public Long retrieveUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails) {
            String userEmail = ((UserDetails)principal).getUsername();
            User user = userDbService.findUserByEmail(userEmail);
            Long userId = user.getUserId();
            return userId;
        } else {

        }
        return null;
    }
}
