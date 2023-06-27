package com.example.demo.core.dal.dao.entity;

import com.example.demo.dto.in.stock.ShoeData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shoe {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private BigInteger id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private ShoeData.Color color;


}
