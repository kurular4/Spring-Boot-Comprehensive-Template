package com.example.demo.exception.document;

import com.example.demo.constant.ErrorMessageConstant;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException() {
        super(ErrorMessageConstant.DOCUMENT_NOT_FOUND);
    }
}
