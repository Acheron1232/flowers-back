package com.acheron.flowers.security.exception.custom;

public class EmailAlreadyExists extends RuntimeException{
    public EmailAlreadyExists() {
        super();
    }

    public EmailAlreadyExists(String message) {
        super(message);
    }
}
