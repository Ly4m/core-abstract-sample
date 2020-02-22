package com.example.demo.controller;

import com.example.demo.StockCapacityException;
import com.example.demo.dto.in.stock.ShoeData;
import com.example.demo.dto.in.stock.StockRows;
import com.example.demo.dto.out.stock.Stock;
import com.example.demo.facade.StockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@Controller
@RequestMapping(path = "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockFacade stockFacade;

    @GetMapping
    public ResponseEntity<Stock> find(@RequestHeader BigInteger version) {
        return ResponseEntity.ok(stockFacade.get(version).get());
    }

    @PatchMapping("shoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addShoeToStock(@RequestHeader BigInteger version, @Valid @RequestBody ShoeData shoeData) throws StockCapacityException {
        stockFacade.get(version).replaceStock(shoeData);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStock(@RequestHeader BigInteger version, @Valid @RequestBody StockRows stock) throws StockCapacityException {
        stockFacade.get(version).
                updateCompleteStock(stock);
    }


}
