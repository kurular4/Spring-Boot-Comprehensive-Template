package com.example.demo.exception.user;

import com.example.demo.constant.ErrorMessageConstant;

public class EmailExistsException extends RuntimeException {

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super(ErrorMessageConstant.EXISTING_EMAIL);
    }
}
