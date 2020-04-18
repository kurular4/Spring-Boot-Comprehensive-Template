package com.example.demo.exception.user;

import com.example.demo.constant.ErrorMessageConstant;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(String message) {
        super(message);
    }

    public UsernameExistsException() {
        super(ErrorMessageConstant.EXISTING_USERNAME);
    }
}
