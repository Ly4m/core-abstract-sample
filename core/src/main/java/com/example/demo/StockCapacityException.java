package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StockCapacityException extends Exception{

    private static final String ERROR_MESSAGE = "Stock capacity is maxed out at ";


    public StockCapacityException(int maxCapacity) {
        super(ERROR_MESSAGE + maxCapacity);
    }
}
