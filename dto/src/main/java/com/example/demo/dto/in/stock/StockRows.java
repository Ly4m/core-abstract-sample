package com.example.demo.dto.in.stock;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class StockRows {

    @NotNull(message = "Stock is mandatory")
    private List<StockRow> stock;

}
