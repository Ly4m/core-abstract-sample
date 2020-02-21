package com.example.demo.dto.in;

import java.math.BigInteger;

public class ShoeFilter {

  public BigInteger size;
  public Color color;

  public enum Color{

    BLACK,
    BLUE,
    ;

  }

}
