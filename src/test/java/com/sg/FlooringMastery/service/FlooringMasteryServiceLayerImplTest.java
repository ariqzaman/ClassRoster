package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryPersistenceException;
import com.sg.FlooringMastery.Dto.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FlooringMasteryServiceLayerImplTest {
    FlooringMasteryServiceLayer testService;

    public FlooringMasteryServiceLayerImplTest() {}

    @BeforeEach
    void setUp() {
        testService = new FlooringMasteryServiceLayerImpl();
    }

    @Test
    void updateNewOrderWithCustomerName() {
        Order newOrder = new Order();
        try {
            testService.updateNewOrderWithCustomerName(newOrder, "My Order");
        } catch (CustomerNameInvalidException e) {
            fail("Customer Name was valid. No exception should have been thrown." + e);
        }
        try {
            testService.updateNewOrderWithCustomerName(newOrder, "My, Order.");
        } catch (CustomerNameInvalidException e) {
            fail("Customer Name was valid. No exception should have been thrown." + e.getMessage());
        }
        try {
            testService.updateNewOrderWithCustomerName(newOrder, "# My Order $$");
            fail("Expected CustomerNameInvalidException was not thrown.");
        } catch (CustomerNameInvalidException e) {
            return;
        }
        try {
            testService.updateNewOrderWithCustomerName(newOrder, "");
            fail("Expected CustomerNameInvalidException was not thrown.");
        } catch (CustomerNameInvalidException e) {
            return;
        }
        try {
            testService.updateNewOrderWithCustomerName(newOrder, " ");
            fail("Expected CustomerNameInvalidException was not thrown.");
        } catch (CustomerNameInvalidException e) {
            return;
        }
    }

    @Test
    void updateNewOrderWithState() {
        Order newOrder = new Order();
        try {
            testService.updateNewOrderWithState(newOrder, "CA");
        } catch (StateNotTaxableException |
                 FlooringMasteryPersistenceException e) {
            fail("State was valid. No exception should have been thrown." + e);
        }
        try {
            testService.updateNewOrderWithState(newOrder, "NY");
            fail("Expected StateNotTaxableException was not thrown.");
        } catch (StateNotTaxableException |
                 FlooringMasteryPersistenceException e) {
            return;
        }
    }

    @Test
    void updateNewOrderWithProductType() {
        Order newOrder = new Order();
        try {
            testService.updateNewOrderWithProductType(newOrder, "Tile");
        } catch (ProductTypeNotAvailableException |
                 FlooringMasteryPersistenceException e) {
            fail("Product Type was valid. No exception should have been thrown." + e);
        }
        try {
            testService.updateNewOrderWithProductType(newOrder, "Pizza");
            fail("Expected ProductTypeNotAvailableException was not thrown.");
        } catch (ProductTypeNotAvailableException |
                 FlooringMasteryPersistenceException e) {
            return;
        }
    }

    @Test
    void updateNewOrderWithArea() {
        Order newOrder = new Order();
        try {
            testService.updateNewOrderWithArea(newOrder, new BigDecimal("150"));
        } catch (MinimumOrderAreaNotMetException e) {
            fail("Order Area was valid. No exception should have been thrown." + e);
        }
        try {
            testService.updateNewOrderWithArea(newOrder, new BigDecimal("100"));
        } catch (MinimumOrderAreaNotMetException e) {
            fail("Order Area was valid. No exception should have been thrown." + e);
        }
        try {
            testService.updateNewOrderWithArea(newOrder, new BigDecimal("99"));
            fail("Expected MinimumOrderAreaNotMetException was not thrown.");
        } catch (MinimumOrderAreaNotMetException e) {
            return;
        }
        try {
            testService.updateNewOrderWithArea(newOrder, new BigDecimal("0"));
            fail("Expected MinimumOrderAreaNotMetException was not thrown.");
        } catch (MinimumOrderAreaNotMetException e) {
            return;
        }
        try {
            testService.updateNewOrderWithArea(newOrder, new BigDecimal("-100"));
            fail("Expected MinimumOrderAreaNotMetException was not thrown.");
        } catch (MinimumOrderAreaNotMetException e) {
            return;
        }
    }
}
