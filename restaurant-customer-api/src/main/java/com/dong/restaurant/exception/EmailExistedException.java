package com.dong.restaurant.exception;

public class EmailExistedException extends RuntimeException{
    public EmailExistedException(String email){
        super("Email is already registered "+email);
    }
}
