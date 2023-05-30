package com.inditex.inditexwebclient.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorManager {

    @ExceptionHandler(value = ApiResponseError.class)
    ResponseEntity<BaseResponse> apiResponseError(ApiResponseError apiResponseError) {
        BaseResponse baseErrorResponse = BaseResponse.builder()
                .status(apiResponseError.getStatus())
                .message(apiResponseError.getMessage()).build();
        return new ResponseEntity<>(baseErrorResponse, HttpStatus.valueOf(baseErrorResponse.getStatus()));
    }
}
