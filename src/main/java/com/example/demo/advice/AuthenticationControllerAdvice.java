package com.example.demo.advice;

import com.example.demo.constant.ErrorMessageCodeConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationControllerAdvice {

    @Autowired
    GenericResponseService genericResponseService;

    @ExceptionHandler(BadCredentialsException.class)
    public GenericResponse handleBadCredentials(BadCredentialsException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.BAD_CREDENTIALS_ERROR);
    }
}
