package com.example.demo.core;

import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.StockFilter;
import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.out.Stock;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class StockCoreNext extends AbstractStockCore {

    @Override
    protected BigInteger getVersion() {
        return BigInteger.valueOf(3);
    }

    @Override
    public Stock get() {
        return Stock.builder()
                .state(StockFilter.StockState.SOME)
                .shoes(
                        List.of(
                                Shoe.builder()
                                        .name("Next Shoe")
                                        .color(ShoeFilter.Color.BLACK)
                                        .size(BigInteger.TWO)
                                        .build()
                        )
                )
                .build();
    }
}
