package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.repository.UserRepository;
import com.janflpk.collectionsmanager.backend.service.UserDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDbServiceTest {

    @Autowired
    private UserDbService userDbService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldSveUserTest() {
        //Given
        User user = new User("Name", "Surname", "birthday",
                "email", "login", "password");
        User userPersisted = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");

        when(userRepository.save(user)).thenReturn(userPersisted);

        //When
        User userReturned = userDbService.saveUser(user);

        //Then
        Assert.assertNotNull(userReturned);
        Assert.assertEquals(Long.valueOf(1), userReturned.getUserId());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldDeleteUserTest() {
        //Given
        User userPersisted = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");

        //When
        userDbService.deleteUser(userPersisted.getUserId());

        //Then
        verify(userRepository, times(1)).deleteById(eq(userPersisted.getUserId()));
    }

    @Test
    public void shouldFetchUserByIdTest() {
        //Given
        User userPersisted = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");

        when(userRepository.findById(userPersisted.getUserId())).thenReturn(Optional.of(userPersisted));

        //When
        User userReturned = userDbService.findById(userPersisted.getUserId());

        //Then
        verify(userRepository, times(1)).findById(userPersisted.getUserId());

        Assert.assertNotNull(userReturned);
        Assert.assertEquals(Long.valueOf(1), userPersisted.getUserId());
        Assert.assertEquals("Name", userReturned.getFirstName());
    }

    @Test
    public void shouldFetchUserByEmailTest() {
        //Given
        User userPersisted = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");

        when(userRepository.getUserByEmail(userPersisted.getEmail())).thenReturn(userPersisted);

        //When
        User userReturned = userDbService.findUserByEmail("email");

        //Then
        verify(userRepository, times(1)).getUserByEmail(eq("email"));

        Assert.assertNotNull(userReturned);
        Assert.assertEquals(Long.valueOf(1), userReturned.getUserId());
        Assert.assertEquals("Name", userReturned.getFirstName());
        Assert.assertEquals("email", userReturned.getEmail());
    }
}
