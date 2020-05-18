package com.janflpk.collectionsmanager.repository;

import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFetchUserByEmailTest() {
        //Given
        User user = new User("Name", "Surname", "1973-01-20", "test@email.test",
                "login", "password");
        userRepository.save(user);

        //When
        User userReturned = userRepository.getUserByEmail("test@email.test");

        //Then
        Assert.assertNotNull(userReturned);
        Assert.assertNotNull(userReturned.getUserId());
        Assert.assertEquals("test@email.test", userReturned.getEmail());

        //Clean up
        userRepository.deleteById(userReturned.getUserId());

    }
}
