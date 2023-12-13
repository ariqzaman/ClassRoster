package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryDao;
import com.sg.FlooringMastery.Dto.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    FlooringMasteryDao dao;

    @Override
    public Order updateNewOrderWithOrderDate(Order order, LocalDate date) throws OrderDateNotInFutureException {
        return null;
    }

    @Override
    public Order updateNewOrderWithCustomerName(Order order, String name) throws CustomerNameInvalidException {
        return null;
    }

    @Override
    public Order updateNewOrderWithState(Order order, String state) throws StateNotTaxbleException {
        return null;
    }

    @Override
    public Order updateNewOrderWithProductType(Order order, String productType) throws ProductTypeNotAvailableException {
        return null;
    }

    @Override
    public Order updateNewOrderWithArea(Order order, BigDecimal area) throws MinimumOrderSizeNotMetException {
        return null;
    }

    @Override
    public Order confirmOrder(Order order) {
        return null;
    }
}
