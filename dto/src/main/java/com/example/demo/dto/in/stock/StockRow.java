package com.example.demo.dto.in.stock;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Value
public class StockRow {

    @NotEmpty(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Size is mandatory")
    private Integer size;

    @NotNull(message = "Color is mandatory")
    private ShoeData.Color color;

    @Positive(message = "Quantity cannot be negative")
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

}
