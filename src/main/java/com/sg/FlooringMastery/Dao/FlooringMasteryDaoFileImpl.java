package com.sg.FlooringMastery.Dao;

import com.sg.FlooringMastery.Dto.Order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    private int autoIncrement = 1;
    Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(String OrderDate, String CustomerName, String State, String ProductType, BigDecimal Area) throws FlooringMasteryPersistenceException {
        return null;
    }

    @Override
    public Order editOrder(String OrderDate, int OrderNumber) throws FlooringMasteryPersistenceException {
        return null;
    }

    @Override
    public Order removeOrder(String OrderDate, int OrderNumber) throws FlooringMasteryPersistenceException {
        return null;
    }
}
