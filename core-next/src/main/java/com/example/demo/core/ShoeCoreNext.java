package com.example.demo.core;

import com.example.demo.dto.out.Shoe;
import com.example.demo.dto.in.ShoeFilter;
import com.example.demo.dto.in.ShoeFilter.Color;
import com.example.demo.dto.out.Shoes;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoeCoreNext extends AbstractShoeCore {

    @Override
    protected BigInteger getVersion() {
        return BigInteger.valueOf(3);
    }

    @Override
    public Shoes search(final ShoeFilter filter) {
        return Shoes.builder()
                .shoes(
                        List.of(
                                Shoe.builder()
                                        .name("New shoe")
                                        .color(Color.BLACK)
                                        .size(BigInteger.TWO)
                                        .build()
                        )
                )
                .build();
    }
}
