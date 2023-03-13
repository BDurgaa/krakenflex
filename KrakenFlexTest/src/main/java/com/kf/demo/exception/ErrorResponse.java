package com.kf.demo.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    public ErrorResponse(String message, String errorCode, List<String> details) {
        super();
        this.message = message;
        this.errorCode = errorCode;
        this.details = details;
    }

    private String message;
    private String errorCode;
    private List<String> details;
}
