package com.example.demo;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTests {

    @Autowired
    AdminService adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private UserDTO userDTO1, userDTO2, userDTO3;

    @Before
    public void init() {
        userDTO1 = new UserDTO()
                .setUsername("xxxxx")
                .setEmail("yyy@gmail.com")
                .setPassword("abc123")
                .setRoles(Arrays.asList(Role.ADMIN));

        userDTO2 = new UserDTO()
                .setUsername("xxxxx")
                .setEmail("yyy@gmail.com")
                .setPassword("abc123")
                .setRoles(null);

        userDTO3 = new UserDTO()
                .setUsername("xxxxx")
                .setEmail("yyy@gmail.com")
                .setPassword("abc123")
                .setRoles(new ArrayList<>());

        User user = new User()
                .setUsername("xxxxx")
                .setEmail("yyy@gmail.com")
                .setPassword("xxxxx")
                .setRoles(Arrays.asList(Role.USER));

        userRepository.save(user);
    }

    @After
    public void reset() {
        userRepository.deleteAll();
    }

    @Test
    public void setRolesWithProperRolesList() {
        adminService.setRoles(userDTO1);
        List<Role> roleList = userRepository.findByUsername(userDTO1.getUsername()).get().getRoles();
        Assert.assertEquals(userDTO1.getRoles(), roleList);
    }

}
