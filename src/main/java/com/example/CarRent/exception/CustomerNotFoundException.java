package com.example.CarRent.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id){
        super("Couldn't find customer "+id);
    }

    public CustomerNotFoundException(String firstname, String lastname){
        super("Couldn't find customer "+firstname +" "+lastname);
    }
}
