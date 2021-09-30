package com.janflpk.collectionsmanager.backend.security;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public Long getUsernameId() {
        return user.getUserId();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    };

    @Override
    public boolean isAccountNonLocked() {
        return true;
    };

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    };

    @Override
    public boolean isEnabled() {
        return true;
    };
}
