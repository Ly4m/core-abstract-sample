package com.example.demo.exceptionHandling;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    protected ResponseEntity<Object> handleConflict(InvalidFormatException ex) {
        return ResponseEntity.badRequest().body("Invalid color provided.");
    }

}
