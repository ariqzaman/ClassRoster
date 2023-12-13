package com.sg.FlooringMastery.Dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order {
    private int OrderNumber;
    private String CustomerName;

    private String State;

    private BigDecimal TaxRate;

    private String ProductType;

    private BigDecimal Area;

    private BigDecimal CostPerSquareFoot;

    private BigDecimal LaborCostPerSquareFoot;

    public Order(int OrderNumber){ this.OrderNumber = OrderNumber; }

    public  Order(){}

    public int getOrderNumber() {
        return OrderNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        TaxRate = taxRate;
    }

    public BigDecimal getArea() {
        return Area;
    }

    public void setArea(BigDecimal area) {
        Area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public BigDecimal getLaborCost() {
        return Area.multiply(LaborCostPerSquareFoot);
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return Area.multiply(CostPerSquareFoot);
    }

    public BigDecimal getTax() {
        return getLaborCost().add(getMaterialCost()).multiply(TaxRate.divide(new BigDecimal(100), RoundingMode.HALF_DOWN)) ;
    }

    public BigDecimal getTotal() {
        return getLaborCost().add(getMaterialCost().add(getTax()));
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        CostPerSquareFoot = costPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        LaborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
    }

}
