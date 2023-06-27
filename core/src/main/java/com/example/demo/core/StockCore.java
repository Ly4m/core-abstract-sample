package com.example.demo.core;

import com.example.demo.StockCapacityException;
import com.example.demo.dto.in.stock.ShoeData;
import com.example.demo.dto.in.stock.StockRows;
import com.example.demo.dto.out.stock.Stock;

public interface StockCore {

    Stock get();

    void updateCompleteStock(StockRows stock) throws StockCapacityException;

    void replaceStock(ShoeData shoeData) throws StockCapacityException;

}
