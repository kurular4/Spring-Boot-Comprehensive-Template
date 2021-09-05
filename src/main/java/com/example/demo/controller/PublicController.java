package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PublicController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "signin", consumes = "application/json")
    public GenericResponse login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return authenticationService.login(userLoginDTO);
    }

}
