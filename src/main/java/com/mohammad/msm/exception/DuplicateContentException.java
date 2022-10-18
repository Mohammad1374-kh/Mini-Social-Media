package com.mohammad.msm.exception;

public class DuplicateContentException extends RuntimeException{

    private static final long serialVersionUID = -7816129112431564887L;

    private String message;

    public DuplicateContentException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
