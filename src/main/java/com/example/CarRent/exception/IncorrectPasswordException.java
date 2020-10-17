package com.example.CarRent.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(){
        super("Incorrect password. The password must be at least 8 characters long. The password must contain special characters, uppercase and lowercase letters");
    }

}
