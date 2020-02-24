package com.example.demo.dto.in.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ShoeData {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Size")
    @Min(value = 0, message = "Size can't be negative")
    private Integer size;

    @NotNull(message = "color is mandatory")
    private Color color;

    public enum Color {

        @JsonProperty("BLACK")
        BLACK,
        @JsonProperty("BLUE")
        BLUE,

    }

}
