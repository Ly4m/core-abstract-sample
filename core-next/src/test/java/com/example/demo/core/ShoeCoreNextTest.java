package com.example.demo.core;

import com.example.demo.StockCapacityException;
import com.example.demo.core.dal.dao.StockDao;
import com.example.demo.core.dal.dao.entity.ShoeQuantity;
import com.example.demo.dto.in.stock.ShoeData;
import com.example.demo.dto.out.stock.Stock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShoeCoreNextTest {

    @Mock
    StockDao stockDao;

    @InjectMocks
    StockCoreNext stockCoreNext;

    @Test
    @DisplayName("Should fetch some shoes")
    void should_fetch_some_shoes() {

        when(stockDao.findAll()).thenReturn(MockBuilder.generateStockMock());

        Stock stock = stockCoreNext.get();

        assertThat(stock).isNotNull();
        assertThat(stock).extracting(Stock::getShoes).asList().hasSize(2);
        assertThat(stock).extracting(Stock::getState).isEqualTo(Stock.StockState.SOME);

    }

    @Test
    @DisplayName("Should fetch no shoes")
    void should_fetch_no_shoes() {

        when(stockDao.findAll()).thenReturn(List.of());

        Stock stock = stockCoreNext.get();

        assertThat(stock).isNotNull();
        assertThat(stock).extracting(Stock::getShoes).asList().isEmpty();
        assertThat(stock).extracting(Stock::getState).isEqualTo(Stock.StockState.EMPTY);
    }

    @Test
    @DisplayName("Should fetch a full stock of one kind")
    void should_fetch_all_shoes_one_kind() {

        when(stockDao.findAll()).thenReturn(MockBuilder.generateStockFullForOneShoeMock());

        Stock stock = stockCoreNext.get();

        assertThat(stock).isNotNull();
        assertThat(stock).extracting(Stock::getShoes).asList().hasSize(1);
        assertThat(stock).extracting(Stock::getState).isEqualTo(Stock.StockState.FULL);
    }

    @Test
    @DisplayName("Should fetch a full stock of many kind")
    void should_fetch_all_shoes_different_kind() {

        when(stockDao.findAll()).thenReturn(MockBuilder.generateStockFullForDifferentShoesMock());

        Stock stock = stockCoreNext.get();

        assertThat(stock).isNotNull();
        assertThat(stock).extracting(Stock::getShoes).asList().hasSize(2);
        assertThat(stock).extracting(Stock::getState).isEqualTo(Stock.StockState.FULL);
    }

    @Test
    @DisplayName("Should add one shoe to existing one")
    void should_add_one_shoe_to_existing_one() throws Exception {
        ShoeData shoe = MockBuilder.generateShoeData();

        when(stockDao.findByShoe_NameAndShoe_ColorAndShoe_Size(shoe.getName(), shoe.getColor(), shoe.getSize()))
                .thenReturn(Optional.of(MockBuilder.generateShoeQuantity()));

        when(stockDao.findAll()).thenReturn(MockBuilder.generateShoeQuantities());

        stockCoreNext.replaceStock(shoe);

        ArgumentCaptor<ShoeQuantity> shoeArgumentCaptor = ArgumentCaptor.forClass(ShoeQuantity.class);

        verify(stockDao).findAll();
        verify(stockDao).findByShoe_NameAndShoe_ColorAndShoe_Size(shoe.getName(), shoe.getColor(), shoe.getSize());
        verify(stockDao).save(shoeArgumentCaptor.capture());
        verifyNoMoreInteractions(stockDao);

        assertThat(shoeArgumentCaptor.getValue()).extracting(ShoeQuantity::getQuantity).isEqualTo(13);

    }

    @Test
    @DisplayName("Should add one shoe to the stock")
    void should_add_one_shoe_to_the_stock() throws Exception {
        ShoeData shoe = MockBuilder.generateShoeData();

        when(stockDao.findByShoe_NameAndShoe_ColorAndShoe_Size(shoe.getName(), shoe.getColor(), shoe.getSize()))
                .thenReturn(Optional.empty());

        when(stockDao.findAll()).thenReturn(MockBuilder.generateShoeQuantities());

        stockCoreNext.replaceStock(shoe);

        ArgumentCaptor<ShoeQuantity> shoeArgumentCaptor = ArgumentCaptor.forClass(ShoeQuantity.class);

        verify(stockDao).findAll();
        verify(stockDao).findByShoe_NameAndShoe_ColorAndShoe_Size(shoe.getName(), shoe.getColor(), shoe.getSize());
        verify(stockDao).save(shoeArgumentCaptor.capture());
        verifyNoMoreInteractions(stockDao);

        assertThat(shoeArgumentCaptor.getValue()).extracting(ShoeQuantity::getQuantity).isEqualTo(1);

    }

    @Test
    @DisplayName("Should prevent to add too many shoes")
    void should_prevent_to_add_too_many_shoes() {
        ShoeData shoe = MockBuilder.generateShoeData();
        List<ShoeQuantity> shoeQuantities = MockBuilder.generateShoeQuantities();
        shoeQuantities.get(1).setQuantity(18);

        when(stockDao.findAll()).thenReturn(shoeQuantities);

        assertThatThrownBy(() -> stockCoreNext.replaceStock(shoe)).isInstanceOf(StockCapacityException.class);

        verify(stockDao).findAll();
        verifyNoMoreInteractions(stockDao);
    }

    @Test
    @DisplayName("Should update whole stock")
    void should_update_whole_stock() throws StockCapacityException {
        stockCoreNext.updateCompleteStock(MockBuilder.generateRows());

        verify(stockDao).deleteAll();
        verify(stockDao).saveAll(anyList());
        verifyNoMoreInteractions(stockDao);
    }

    @Test
    @DisplayName("Should update whole stock")
    void should_not_update_whole_stock_because_capacity() {
        assertThatThrownBy(() ->
                stockCoreNext.updateCompleteStock(MockBuilder.generateRowsWithTooMuchStock()))
                .isInstanceOf(StockCapacityException.class);

        verifyNoInteractions(stockDao);

    }


}