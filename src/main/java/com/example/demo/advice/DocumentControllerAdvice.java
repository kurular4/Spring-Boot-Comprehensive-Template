package com.example.demo.advice;

import com.example.demo.constant.ErrorMessageCodeConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.exception.document.DocumentNotFoundException;
import com.example.demo.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DocumentControllerAdvice {

    @Autowired
    GenericResponseService genericResponseService;

    @ExceptionHandler(DocumentNotFoundException.class)
    public GenericResponse handleDocumentNotFound(DocumentNotFoundException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.NOT_FOUND);
    }
}
