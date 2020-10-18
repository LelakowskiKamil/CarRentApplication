package com.example.CarRent.controller;

import com.example.CarRent.exception.CarNotFoundException;
import com.example.CarRent.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = IllegalExceptionProcessing.class)
public class IllegalArgumentAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<?> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

}
