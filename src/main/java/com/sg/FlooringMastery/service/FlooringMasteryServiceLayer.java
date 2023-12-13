package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryDao;
import com.sg.FlooringMastery.Dto.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FlooringMasteryServiceLayer {
    public Order updateNewOrderWithOrderDate(Order order, LocalDate date) throws OrderDateNotInFutureException;
    public Order updateNewOrderWithCustomerName(Order order, String name) throws CustomerNameInvalidException;
    public Order updateNewOrderWithState(Order order, String name) throws StateNotTaxbleException;
    public Order updateNewOrderWithProductType(Order order, String product) throws ProductTypeNotAvailableException;
    public Order updateNewOrderWithArea(Order order, BigDecimal area) throws MinimumOrderSizeNotMetException;
    public Order confirmOrder(Order order);
}
