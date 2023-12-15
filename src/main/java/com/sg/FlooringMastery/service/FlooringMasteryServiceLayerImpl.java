package com.sg.FlooringMastery.service;

import com.sg.FlooringMastery.Dao.FlooringMasteryDao;
import com.sg.FlooringMastery.Dao.FlooringMasteryDaoFileImpl;

import com.sg.FlooringMastery.Dao.FlooringMasteryPersistenceException;
import com.sg.FlooringMastery.Dto.Order;
import com.sg.FlooringMastery.Dto.Products;
import com.sg.FlooringMastery.Dto.Tax;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.Scanner;
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

    //---------------------------------------------------------------------------------------------------------

    public Order updateOrder(Order orders) throws CustomerNameInvalidException, FlooringMasteryPersistenceException, StateNotTaxableException, ProductTypeNotAvailableException, MinimumOrderAreaNotMetException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter date (ex. 02/07/2020):");
        String customerDate = sc.nextLine();
        String fileName = dao.getOrderDateFile(customerDate);


        System.out.println("What is the order number?:");
        int customerNum = Integer.parseInt(sc.nextLine());
        System.out.println("Enter customer name (ex. Ada Lovelace):");
        String customerName = sc.nextLine();
        System.out.println("Enter customer state (ex. NY):");
        String customerState = sc.nextLine();
        System.out.println("Enter product (ex. Wood):");
        String customerProduct = sc.nextLine();
        System.out.println("Enter Area (ex. 150):");
        String customerArea = sc.nextLine();

        //should display edit order as it goes through changes ive
        if(!customerName.isEmpty()){
            updateNewOrderWithCustomerName(orders, customerName);
        }
        if(!customerState.isEmpty()){
            updateNewOrderWithState(orders, customerState);
            //recalculate tax
            List<Tax> taxLookup = dao.getTaxes();
            for(Tax currState: taxLookup){
                if(currState.getStateAbbreviation().equals(customerState)){
                    orders.setTaxRate(currState.getTaxRate());
                }
            }
        }
        if(!customerProduct.isEmpty()){
            updateNewOrderWithProductType(orders, customerProduct);
            //recalculate cpsf and lcpsf
            List<Products> productLookup = dao.getProducts();
            for(Products currProduct: productLookup){
                if(currProduct.getProductType().equals(customerProduct)){
                    orders.setCostPerSquareFoot(currProduct.getCostPerSquareFoot());
                    orders.setLaborCostPerSquareFoot(currProduct.getLaborCostPerSquareFoot());
                }
            }
        }

        if(!customerArea.isEmpty()){
            BigDecimal newArea = new BigDecimal(customerArea);
            updateNewOrderWithArea(orders, newArea);
            orders.setArea(newArea);
            //recalculate area
            List<Products> productLookup = dao.getProducts();
            for(Products currProduct: productLookup){
                if(currProduct.getProductType().equals(customerProduct)){
                    orders.setCostPerSquareFoot(currProduct.getCostPerSquareFoot());
                    orders.setLaborCostPerSquareFoot(currProduct.getLaborCostPerSquareFoot());
                }
            }
        }
        System.out.println("Should the edit be saved? (Yes/No): ");
        System.out.println(orders);
        String save = sc.nextLine();

        if (save.equals("Yes")){
            dao.replaceOrder(customerNum,orders);
            //return to menu
        }else {
            //return to menu
        }
        return orders;
    }

    @Override
    public Order removeOrder(int orderNum) throws FlooringMasteryPersistenceException {
        return dao.removeOrder(orderNum);
    }

}


