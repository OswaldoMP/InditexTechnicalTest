package com.inditex.inditexwebclient.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BaseResponse {
    private int status;
    private String message;
}
