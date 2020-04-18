package com.example.demo.exception.user;

import com.example.demo.constant.ErrorMessageConstant;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super(ErrorMessageConstant.USER_NOT_FOUND);
    }
}
