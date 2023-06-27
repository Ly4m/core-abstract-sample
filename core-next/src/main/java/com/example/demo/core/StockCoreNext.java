package com.example.demo.core;

import com.example.demo.StockCapacityException;
import com.example.demo.core.dal.dao.StockDao;
import com.example.demo.core.dal.dao.entity.Shoe;
import com.example.demo.core.dal.dao.entity.ShoeQuantity;
import com.example.demo.dto.in.stock.ShoeData;
import com.example.demo.dto.in.stock.StockRow;
import com.example.demo.dto.in.stock.StockRows;
import com.example.demo.dto.out.stock.Stock;
import com.example.demo.dto.out.stock.StockLine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Component
@AllArgsConstructor
public class StockCoreNext extends AbstractStockCore {

    private static final int STOCK_MAX_CAPACITY = 30;
    private static final int STOCK_MIN_CAPACITY = 0;
    private static final int CORE_VERSION = 3;

    private StockDao stockDao;

    @Override
    protected BigInteger getVersion() {
        return BigInteger.valueOf(CORE_VERSION);
    }

    @Override
    public Stock get() {
        List<StockLine> stocks = StreamSupport.stream(stockDao.findAll().spliterator(), false)
                .map(this::getStockLineFromShoeQuantity).collect(Collectors.toList());

        Stock.StockState state = computeState(stocks);

        return Stock.builder()
                .shoes(stocks)
                .state(state).build();
    }

    @Override
    public void updateCompleteStock(final StockRows completeStock) throws StockCapacityException {
        int stockTotal = completeStock.getStock().stream().mapToInt(StockRow::getQuantity).sum();
        if (stockTotal > STOCK_MAX_CAPACITY) {
            throw new StockCapacityException(STOCK_MAX_CAPACITY);
        }

        List<ShoeQuantity> newShoeQuantity = completeStock.getStock()
                .stream()
                .map(this::getShoeQuantityFromShoeStock)
                .collect(Collectors.toList());

        stockDao.deleteAll();
        stockDao.saveAll(newShoeQuantity);
    }


    @Override
    public void replaceStock(final ShoeData shoeData) throws StockCapacityException {
        checkStockCapacity();
        updateStock(shoeData);
    }

    private void updateStock(ShoeData shoeData) {
        stockDao.findByShoe_NameAndShoe_ColorAndShoe_Size(shoeData.getName(), shoeData.getColor(), shoeData.getSize())
                .ifPresentOrElse(
                        (shoeQuantity) -> {
                            shoeQuantity.setQuantity(shoeQuantity.getQuantity() + 1);
                            stockDao.save(shoeQuantity);
                        },
                        () -> {
                            ShoeQuantity shoeQuantity = getShoeQuantityFromShoeData(shoeData);
                            stockDao.save(shoeQuantity);
                        }
                );
    }

    private StockLine getStockLineFromShoeQuantity(final ShoeQuantity shoeQuantity) {
        return StockLine.builder()
                .color(shoeQuantity.getShoe().getColor())
                .name(shoeQuantity.getShoe().getName())
                .size(shoeQuantity.getShoe().getSize())
                .quantity(shoeQuantity.getQuantity())
                .build();
    }

    private ShoeQuantity getShoeQuantityFromShoeData(final ShoeData shoeData) {
        ShoeQuantity shoeQuantity = new ShoeQuantity();
        Shoe shoe = Shoe.builder()
                .name(shoeData.getName())
                .color(shoeData.getColor())
                .size(shoeData.getSize())
                .build();

        shoeQuantity.setShoe(shoe);
        shoeQuantity.setQuantity(1);

        return shoeQuantity;
    }

    private ShoeQuantity getShoeQuantityFromShoeStock(final StockRow shoeStock) {
        ShoeQuantity shoeQuantity = new ShoeQuantity();
        Shoe shoe = Shoe.builder()
                .name(shoeStock.getName())
                .color(shoeStock.getColor())
                .size(shoeStock.getSize())
                .build();

        shoeQuantity.setShoe(shoe);
        shoeQuantity.setQuantity(shoeStock.getQuantity());

        return shoeQuantity;
    }

    private Stock.StockState computeState(final List<StockLine> stocks) {
        int totalQuantity = stocks.stream().mapToInt(StockLine::getQuantity).sum();
        Stock.StockState state;

        switch (totalQuantity) {
            case STOCK_MIN_CAPACITY:
                state = Stock.StockState.EMPTY;
                break;
            case STOCK_MAX_CAPACITY:
                state = Stock.StockState.FULL;
                break;
            default:
                state = Stock.StockState.SOME;
                break;
        }

        return state;
    }

    private void checkStockCapacity() throws StockCapacityException {
        int usedSpace = StreamSupport.stream(stockDao.findAll().spliterator(), false)
                .mapToInt(ShoeQuantity::getQuantity)
                .sum();

        if (STOCK_MAX_CAPACITY == usedSpace) {
            throw new StockCapacityException(STOCK_MAX_CAPACITY);
        }

    }
}
