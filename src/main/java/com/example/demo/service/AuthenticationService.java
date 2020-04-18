package com.example.demo.service;

import com.example.demo.constant.ErrorMessageConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    UserService userService;

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public GenericResponse login(UserLoginDTO userLoginDTO) {
        User user = userService.loadUserByUsername(userLoginDTO.getUsername());

        if (user != null && bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(),
                    userLoginDTO.getPassword()));
            String token = jwtTokenProvider.createToken(userLoginDTO.getUsername(), user.getRoles());
            Map<String,Object> model = new HashMap<>();
            model.put("token", token);
            return genericResponseService.createResponseNoError(model);
        } else throw new BadCredentialsException(ErrorMessageConstant.BAD_CREDENTIALS);
    }
}
