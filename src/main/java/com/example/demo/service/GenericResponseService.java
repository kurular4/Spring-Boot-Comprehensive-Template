package com.example.demo.service;

import com.example.demo.constant.ErrorMessageCodeConstant;
import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.ResponseType;
import org.springframework.stereotype.Service;

@Service
public class GenericResponseService {

    public <T> GenericResponse createResponse(ResponseType responseType, String message, T data, int errorCode) {
        GenericResponse<T> response = new GenericResponse<T>()
                .setData(data)
                .setResponseType(responseType)
                .setMessage(message)
                .setErrorCode(errorCode);
        return response;
    }

    public <T> GenericResponse createResponseNoError(String message, T data) {
        return createResponse(ResponseType.SUCCESS,message,data, ErrorMessageCodeConstant.NO_ERROR);
    }

    public <T> GenericResponse createResponseNoError(T data) {
        return createResponse(ResponseType.SUCCESS,null,data, ErrorMessageCodeConstant.NO_ERROR);
    }

    public GenericResponse createResponseWithError(String message, int errorCode) {
        return createResponse(ResponseType.ERROR,message,null, errorCode);
    }
}
