package com.inditex.inditexwebclient.errors;

import lombok.Data;

@Data
public class ApiResponseError extends RuntimeException{
    private int status;
    private String message;

    public ApiResponseError(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
