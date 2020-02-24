package com.example.demo.core.dal.dao;

import com.example.demo.core.dal.dao.entity.ShoeQuantity;
import com.example.demo.dto.in.stock.ShoeData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface StockDao extends CrudRepository<ShoeQuantity, BigInteger> {

    Optional<ShoeQuantity> findByShoe_NameAndShoe_ColorAndShoe_Size(String name, ShoeData.Color color, Integer size);
}
