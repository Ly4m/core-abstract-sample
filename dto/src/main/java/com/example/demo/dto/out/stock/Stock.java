package com.example.demo.dto.out.stock;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonDeserialize(builder = Stock.StockBuilder.class)
public class Stock {

    private StockState state;
    private List<StockLine> shoes;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockBuilder {

    }

    public enum StockState {
        EMPTY,
        FULL,
        SOME,
        ;
    }
}
