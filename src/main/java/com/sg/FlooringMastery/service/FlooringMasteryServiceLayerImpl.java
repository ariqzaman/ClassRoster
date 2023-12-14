package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryDao;
import com.sg.FlooringMastery.Dao.FlooringMasteryPersistenceException;
import com.sg.FlooringMastery.Dto.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    FlooringMasteryDao dao;

    @Override
    public Order updateNewOrderWithCustomerName(Order order, String name) throws CustomerNameInvalidException {
        if (Pattern.compile("^[a-zA-Z0-9.,]+$").matcher(name).matches()) {
            order.setCustomerName(name);
            return order;
        } else {
            throw new CustomerNameInvalidException();
        }
    }

    @Override
    public Order updateNewOrderWithState(Order order, String stateAbbreviation) throws StateNotTaxbleException, FlooringMasteryPersistenceException {
        if (!dao.getTaxes().stream().filter(tax -> stateAbbreviation.equals(tax.getStateAbbreviation())).toList().isEmpty()) {
            order.setState(stateAbbreviation);
            return order;
        } else {
            throw new StateNotTaxbleException();
        }
    }

    @Override
    public Order updateNewOrderWithProductType(Order order, String productType) throws ProductTypeNotAvailableException, FlooringMasteryPersistenceException {
        if (!dao.getProducts().stream().filter(product -> product.equals(product.getProductType())).toList().isEmpty()) {
            order.setProductType(productType);
            return order;
        } else {
            throw new ProductTypeNotAvailableException();
        }
    }

    @Override
    public Order updateNewOrderWithArea(Order order, BigDecimal area) throws MinimumOrderSizeNotMetException {
        if (new BigDecimal(100).compareTo(area) <= 0) {
            order.setArea(area);
            return order;
        } else {
            throw new MinimumOrderSizeNotMetException();
        }
    }

    @Override
    public Order confirmOrder(Order order) {
        // Writes order to file
        return null;
    }
}
