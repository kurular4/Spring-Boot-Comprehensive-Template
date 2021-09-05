package com.example.demo.service;

import com.example.demo.constant.ErrorMessageConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserService userService;
    private final GenericResponseService genericResponseService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;

    public GenericResponse login(UserLoginDTO userLoginDTO) {
        User user = userService.loadUserByUsername(userLoginDTO.getUsername());

        if (user != null && bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),
                    userLoginDTO.getPassword()));
            return genericResponseService.createResponseNoError(user);
        } else throw new BadCredentialsException(ErrorMessageConstant.BAD_CREDENTIALS);
    }
}
