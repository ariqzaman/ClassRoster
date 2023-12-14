package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryPersistenceException;
import com.sg.FlooringMastery.Dto.Order;

import java.math.BigDecimal;

public interface FlooringMasteryServiceLayer {
    public Order updateNewOrderWithCustomerName(Order order, String name) throws CustomerNameInvalidException;
    public Order updateNewOrderWithState(Order order, String name) throws StateNotTaxableException, FlooringMasteryPersistenceException;
    public Order updateNewOrderWithProductType(Order order, String product) throws ProductTypeNotAvailableException, FlooringMasteryPersistenceException;
    public Order updateNewOrderWithArea(Order order, BigDecimal area) throws MinimumOrderAreaNotMetException;
    public Order confirmOrder(Order order);
}
