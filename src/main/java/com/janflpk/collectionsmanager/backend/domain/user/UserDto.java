package com.janflpk.collectionsmanager.backend.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
