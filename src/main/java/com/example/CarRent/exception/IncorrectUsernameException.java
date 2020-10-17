package com.example.CarRent.exception;

public class IncorrectUsernameException extends RuntimeException {

    public IncorrectUsernameException(){
        super("Incorrect username. The username must be at least 4 characters long. The username can't contain special characters");
    }

}
