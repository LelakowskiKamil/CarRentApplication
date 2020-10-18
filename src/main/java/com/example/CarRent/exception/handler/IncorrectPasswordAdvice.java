package com.example.CarRent.exception.handler;

import com.example.CarRent.exception.CarNotFoundException;
import com.example.CarRent.exception.IncorrectPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectPasswordAdvice {

    @ResponseBody
    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String incorrectPasswordHandler(IncorrectPasswordException ex){
        return ex.getMessage();
    }

}
