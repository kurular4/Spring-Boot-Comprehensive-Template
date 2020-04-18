package com.example.demo.service;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    GenericResponseService genericResponseService;

    public GenericResponse allUsers() {
        return genericResponseService.createResponseNoError(userRepository.findAll());
    }

    public GenericResponse setRoles(UserDTO userDTO) {
        Objects.requireNonNull(userDTO);

        User user = userService.loadUserByUsername(userDTO.getUsername());
        user.setRoles(userDTO.getRoles());

        if (userRepository.save(user) != null) {
            return genericResponseService.createResponseNoError(user);
        } else throw new RuntimeException();
    }
}
