package com.mohammad.msm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(NotFoundException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return response;
    }

    @ResponseBody
    @ExceptionHandler(DuplicateContentException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ExceptionResponse handleDuplicateException(DuplicateContentException e) {
        ExceptionResponse response = new ExceptionResponse(e.getMessage());
        return response;
    }
}