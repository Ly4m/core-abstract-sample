package com.example.demo.dto.out.stock;

import com.example.demo.dto.in.stock.ShoeData;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = StockLine.StockLineBuilder.class)
public class StockLine {

    private String name;
    private Integer size;
    private ShoeData.Color color;
    private Integer quantity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StockLineBuilder {

    }

}
