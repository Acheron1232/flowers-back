package com.acheron.flowers.security.exception;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
    public UserAlreadyExistsException() {
        super();
    }
}
