package com.example.demo.core;

import ch.qos.logback.core.pattern.color.BlueCompositeConverter;
import com.example.demo.core.dal.dao.entity.Shoe;
import com.example.demo.core.dal.dao.entity.ShoeQuantity;
import com.example.demo.dto.in.stock.ShoeData;
import com.example.demo.dto.in.stock.StockRow;
import com.example.demo.dto.in.stock.StockRows;
import com.example.demo.dto.out.stock.Stock;

import java.math.BigInteger;
import java.util.List;

public class MockBuilder {

    static Iterable<ShoeQuantity> generateStockFullForOneShoeMock() {
        Shoe shoe1 = new Shoe();
        shoe1.setId(BigInteger.ONE);
        shoe1.setColor(ShoeData.Color.BLACK);
        shoe1.setName("Name");
        shoe1.setSize(8);

        ShoeQuantity shoeQuantity1 = new ShoeQuantity();
        shoeQuantity1.setId(BigInteger.ONE);
        shoeQuantity1.setQuantity(30);
        shoeQuantity1.setShoe(shoe1);

        return List.of(shoeQuantity1);
    }


    static Iterable<ShoeQuantity> generateStockMock() {

        Shoe shoe1 = new Shoe();
        shoe1.setId(BigInteger.ONE);
        shoe1.setColor(ShoeData.Color.BLACK);
        shoe1.setName("Name");
        shoe1.setSize(8);

        ShoeQuantity shoeQuantity1 = new ShoeQuantity();
        shoeQuantity1.setId(BigInteger.ONE);
        shoeQuantity1.setQuantity(1);
        shoeQuantity1.setShoe(shoe1);

        Shoe shoe2 = new Shoe();
        shoe2.setId(BigInteger.ONE);
        shoe2.setColor(ShoeData.Color.BLACK);
        shoe2.setName("Name");
        shoe2.setSize(8);

        ShoeQuantity shoeQuantity2 = new ShoeQuantity();
        shoeQuantity2.setId(BigInteger.ONE);
        shoeQuantity2.setQuantity(1);
        shoeQuantity2.setShoe(shoe2);

        return List.of(shoeQuantity1, shoeQuantity2);
    }

    static Iterable<ShoeQuantity> generateStockFullForDifferentShoesMock() {

        Shoe shoe1 = new Shoe();
        shoe1.setId(BigInteger.ONE);
        shoe1.setColor(ShoeData.Color.BLACK);
        shoe1.setName("Name");
        shoe1.setSize(8);

        ShoeQuantity shoeQuantity1 = new ShoeQuantity();
        shoeQuantity1.setId(BigInteger.ONE);
        shoeQuantity1.setQuantity(10);
        shoeQuantity1.setShoe(shoe1);

        Shoe shoe2 = new Shoe();
        shoe2.setId(BigInteger.ONE);
        shoe2.setColor(ShoeData.Color.BLACK);
        shoe2.setName("Name");
        shoe2.setSize(8);

        ShoeQuantity shoeQuantity2 = new ShoeQuantity();
        shoeQuantity2.setId(BigInteger.ONE);
        shoeQuantity2.setQuantity(20);
        shoeQuantity2.setShoe(shoe2);

        return List.of(shoeQuantity1, shoeQuantity2);
    }

    static ShoeData generateShoeData() {
        ShoeData shoe = new ShoeData();
        shoe.setName("New shoe");
        shoe.setSize(0);
        shoe.setColor(ShoeData.Color.BLACK);
        return shoe;
    }

    static ShoeQuantity generateShoeQuantity() {

        Shoe shoe1 = Shoe.builder()
                .id(BigInteger.ONE)
                .name("Shoe One")
                .color(ShoeData.Color.BLACK)
                .build();

        ShoeQuantity shoeQuantity1 = new ShoeQuantity();
        shoeQuantity1.setId(BigInteger.ONE);
        shoeQuantity1.setShoe(shoe1);
        shoeQuantity1.setQuantity(12);

        return shoeQuantity1;
    }

    static List<ShoeQuantity> generateShoeQuantities() {

        ShoeQuantity shoe1 = generateShoeQuantity();
        ShoeQuantity shoe2 = generateShoeQuantity();

        shoe2.getShoe().setName("Shoe 2");
        shoe2.setId(BigInteger.TWO);
        shoe2.getShoe().setSize(8);
        shoe2.getShoe().setColor(ShoeData.Color.BLUE);
        shoe2.setQuantity(10);

        return List.of(shoe1, shoe2);

    }

    static StockRows generateRows() {
        StockRow row1 = new StockRow(
                "Shoe 1", 8, ShoeData.Color.BLUE, 10
        );

        StockRow row2 = new StockRow(
          "Shoe 2", 9, ShoeData.Color.BLACK, 10
        );

        StockRows rows = new StockRows();
        rows.setStock(List.of(row1, row2));
        return rows;
    }

    static StockRows generateRowsWithTooMuchStock() {
        StockRow row1 = new StockRow(
                "Shoe 1", 8, ShoeData.Color.BLUE, 10
        );

        StockRow row2 = new StockRow(
          "Shoe 2", 9, ShoeData.Color.BLACK, 25
        );

        StockRows rows = new StockRows();
        rows.setStock(List.of(row1, row2));
        return rows;
    }

}
