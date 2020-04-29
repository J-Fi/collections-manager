package com.janflpk.collectionsmanager.backend.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQuery(
        name = "User.getUserByEmail",
        query = "FROM User WHERE email = :EMAIL"
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday_date")
    private String birthday;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public User(String firstName, String lastName, String birthday, String email, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
