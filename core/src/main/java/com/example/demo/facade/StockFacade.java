package com.example.demo.facade;

import com.example.demo.core.StockCore;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Component
public class StockFacade {

    private Map<BigInteger, StockCore> implementations = new HashMap<>();

    public StockCore get(BigInteger version) {
        return implementations.get(version);
    }

    public void register(BigInteger version, StockCore implementation) {
        this.implementations.put(version, implementation);
    }

}
