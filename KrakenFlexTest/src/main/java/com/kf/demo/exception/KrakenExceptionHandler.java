package com.kf.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class KrakenExceptionHandler {

    /*
        handler to handle exception when invalid url requests
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoHandlerFound(NoHandlerFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("INVALID_URL", HttpStatus.NOT_FOUND.toString(), details);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /*
        handler to handle exceptions when language query param is not passed in request url
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public final ResponseEntity<ErrorResponse> handleMissingServletReqParException(MissingServletRequestParameterException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("INCORRECT_REQUEST", HttpStatus.BAD_REQUEST.toString(), details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handle500Exception(RuntimeException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.toString(), details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}