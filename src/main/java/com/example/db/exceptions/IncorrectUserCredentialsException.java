package com.example.db.exceptions;

public class IncorrectUserCredentialsException extends RuntimeException{
    public IncorrectUserCredentialsException(String massage) {
        super(massage);
    }
}
