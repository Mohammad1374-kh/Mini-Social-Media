package com.mohammad.msm.exception;

public class ExceptionResponse {
    private String error;

    public ExceptionResponse() {

    }

    public ExceptionResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
