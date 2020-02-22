package com.example.demo.core.dal.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
public class ShoeQuantity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private BigInteger id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "shoe_id", referencedColumnName = "id")
    private Shoe shoe;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}
