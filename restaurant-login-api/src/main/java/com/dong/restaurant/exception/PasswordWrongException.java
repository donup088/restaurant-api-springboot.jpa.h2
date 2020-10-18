package com.dong.restaurant.exception;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(){
        super("Password is wrong!!");
    }
}
