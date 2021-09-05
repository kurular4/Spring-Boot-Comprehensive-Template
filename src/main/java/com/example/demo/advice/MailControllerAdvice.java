package com.example.demo.advice;

import com.example.demo.constant.ErrorMessageCodeConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.service.GenericResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@RestControllerAdvice
public class MailControllerAdvice {
    private final GenericResponseService genericResponseService;

    @ExceptionHandler(MessagingException.class)
    public GenericResponse handleMessagingError(MessagingException exception) {
        return genericResponseService.createResponseWithError(exception.getMessage(),
                ErrorMessageCodeConstant.MESSAGING_ERROR);
    }
}
