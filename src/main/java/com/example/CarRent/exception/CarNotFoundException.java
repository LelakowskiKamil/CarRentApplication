package com.example.CarRent.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long id){
        super("Couldn't find car "+id);
    }

}
