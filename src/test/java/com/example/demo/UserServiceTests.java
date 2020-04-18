package com.example.demo;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.user.EmailExistsException;
import com.example.demo.exception.user.UsernameExistsException;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private UserDTO userDTO1, userDTO2, userDTO3;

    @Before
    public void init() {
        userRepository.deleteAll();

        userDTO1 = new UserDTO()
                .setUsername("a1b2c3")
                .setEmail("abcdef@gmail.com")
                .setPassword("abc123")
                .setRoles(Arrays.asList(Role.ADMIN));

        userDTO2 = new UserDTO()
                .setUsername("a1b2c3")
                .setEmail("xxxx@gmail.com")
                .setPassword("xyz123")
                .setRoles(Arrays.asList(Role.USER));

        userDTO3 = new UserDTO()
                .setUsername("ert123")
                .setEmail("xxxx@gmail.com")
                .setPassword("hhh123")
                .setRoles(Arrays.asList(Role.USER));
    }

    @After
    public void reset() {
        userRepository.deleteAll();
    }

    @Test
    public void createUserWithUniqueUsername() {
        userService.create(userDTO1);
        Assert.assertTrue(userRepository.findByUsername(userDTO1.getUsername()).isPresent());
    }

    @Test
    public void createUserWithExistingUsernameThrowsUsernameExistsException() {
        userService.create(userDTO1);
        exception.expect(UsernameExistsException.class);
        userService.create(userDTO2);
    }

    @Test
    public void createUserWithExistingEmailThrowsEmailExistsException() {
        userService.create(userDTO2);
        exception.expect(EmailExistsException.class);
        userService.create(userDTO3);
    }
}
