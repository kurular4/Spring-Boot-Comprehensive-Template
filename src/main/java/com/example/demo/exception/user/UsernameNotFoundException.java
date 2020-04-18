package com.example.demo.exception.user;

import com.example.demo.constant.ErrorMessageConstant;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException() {
        super(ErrorMessageConstant.USERNAME_NOT_FOUND);
    }
}
