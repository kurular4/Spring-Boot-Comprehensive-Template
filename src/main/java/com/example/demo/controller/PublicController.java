package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PublicController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "signin", consumes = "application/json")
    public GenericResponse login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return authenticationService.login(userLoginDTO);
    }

}
