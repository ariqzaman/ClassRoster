package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryDao;
import com.sg.FlooringMastery.Dao.FlooringMasteryDaoFileImpl;
import com.sg.FlooringMastery.Dao.FlooringMasteryPersistenceException;
import com.sg.FlooringMastery.Dto.Order;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    FlooringMasteryDao dao;

    FlooringMasteryServiceLayerImpl() {
        this.dao = new FlooringMasteryDaoFileImpl();
    }

    @Override
    public Order updateNewOrderWithCustomerName(Order order, String name) throws CustomerNameInvalidException {
        if (name == null || name.trim().isEmpty()) {
            throw new CustomerNameInvalidException("Name cannot be blank.");
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9., ]+$");
        boolean matches = pattern.matcher(name).matches();
        if (matches) {
            order.setCustomerName(name);
            return order;
        } else {
            throw new CustomerNameInvalidException("Name contains invalid characters.");
        }
    }

    @Override
    public Order updateNewOrderWithState(Order order, String stateAbbreviation) throws StateNotTaxableException, FlooringMasteryPersistenceException {
        if (!dao.getTaxes().stream().filter(tax -> stateAbbreviation.equals(tax.getStateAbbreviation())).toList().isEmpty()) {
            order.setState(stateAbbreviation);
            return order;
        } else {
            throw new StateNotTaxableException();
        }
    }

    @Override
    public Order updateNewOrderWithProductType(Order order, String productType) throws ProductTypeNotAvailableException, FlooringMasteryPersistenceException {
        if (!dao.getProducts().stream().filter(product -> productType.equals(product.getProductType())).toList().isEmpty()) {
            order.setProductType(productType);
            return order;
        } else {
            throw new ProductTypeNotAvailableException();
        }
    }

    @Override
    public Order updateNewOrderWithArea(Order order, BigDecimal area) throws MinimumOrderAreaNotMetException {
        if (new BigDecimal(100).compareTo(area) <= 0) {
            order.setArea(area);
            return order;
        } else {
            throw new MinimumOrderAreaNotMetException();
        }
    }

    @Override
    public Order confirmOrder(Order order) {
        // Writes order to file
        return null;
    }
}
