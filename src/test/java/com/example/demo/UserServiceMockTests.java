package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceMockTests {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    User user1, user2, user3;

    @Before
    public void init() {
        user1 = new User()
                .setUsername("a1b2c3")
                .setEmail("abcdef@gmail.com")
                .setPassword("abc123")
                .setRoles(Arrays.asList(Role.ADMIN));

        user2 = new User()
                .setUsername("a1b2c3")
                .setEmail("xxxx@gmail.com")
                .setPassword("xyz123")
                .setRoles(Arrays.asList(Role.USER));

        user3 = new User()
                .setUsername("ert123")
                .setEmail("xxxx@gmail.com")
                .setPassword("hhh123")
                .setRoles(Arrays.asList(Role.USER));

        when(userRepository.findByUsername("a1b2c3")).thenReturn(Optional.of(user1));
    }

    @Test
    public void loadByUsername() {
        Assert.assertTrue(userService.loadUserByUsername("a1b2c3").getEmail().equals(user1.getEmail()));
    }


}
