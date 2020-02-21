package com.example.demo.core;

import com.example.demo.facade.StockFacade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

public abstract class AbstractStockCore implements StockCore {

    @Autowired
    private StockFacade stockFacade;

    @PostConstruct
    void init() {
        stockFacade.register(getVersion(), this);
    }

    protected abstract BigInteger getVersion();
}
