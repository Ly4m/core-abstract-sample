package com.example.demo.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class ShoeFilter {

    public BigInteger size;
    public Color color;

    public enum Color {

        @JsonProperty("BLACK")
        BLACK,
        @JsonProperty("BLUE")
        BLUE,

    }

}
