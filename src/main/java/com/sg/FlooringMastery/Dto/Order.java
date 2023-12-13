package com.sg.FlooringMastery.Dto;

import java.math.BigDecimal;

public class Order {
    /*
OrderNumber – Integer
CustomerName – String
State – String
TaxRate – BigDecimal
ProductType – String
Area – BigDecimal
CostPerSquareFoot – BigDecimal
LaborCostPerSquareFoot – BigDecimal
MaterialCost – BigDecimal
LaborCost – BigDecimal
Tax – BigDecimal
Total – BigDecimal
     */
    private int OrderNumber;
    private String CustomerName;

    private String State;

    private BigDecimal TaxRate;

    private String ProductType;

    private BigDecimal Area;

    private BigDecimal CostPerSquareFoot;

    private BigDecimal LaborCostPerSquareFoot;

    private BigDecimal MaterialCost;
    private BigDecimal LaborCost;
    private BigDecimal Tax;
    private BigDecimal Total;

    public Order(int OrderNumber){ this.OrderNumber = OrderNumber; }

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

    //dont think we set tax rate
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
        return LaborCost;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return MaterialCost;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        CostPerSquareFoot = costPerSquareFoot;
    }

    public void setLaborCost(BigDecimal laborCost) {
        LaborCost = laborCost;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        LaborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        MaterialCost = materialCost;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public void setTax(BigDecimal tax) {
        Tax = tax;
    }
}
