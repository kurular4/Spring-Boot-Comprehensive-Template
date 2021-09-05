package com.example.demo.advice;

import com.example.demo.constant.ErrorMessageCodeConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.exception.user.EmailExistsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.exception.user.UsernameExistsException;
import com.example.demo.exception.user.UsernameNotFoundException;
import com.example.demo.service.GenericResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class UserControllerAdvice {
    private final GenericResponseService genericResponseService;

    @ExceptionHandler(UsernameNotFoundException.class)
    public GenericResponse handleUsernameNotFound(UsernameNotFoundException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.NOT_FOUND);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public GenericResponse handleUsernameExists(UsernameExistsException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.USERNAME_EXIST);
    }

    @ExceptionHandler(EmailExistsException.class)
    public GenericResponse handleEmailExists(EmailExistsException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.EMAIL_EXIST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public GenericResponse handleUserNotFound(UserNotFoundException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.NOT_FOUND);
    }
}
