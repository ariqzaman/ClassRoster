package com.sg.FlooringMastery.Dto;

import java.math.BigDecimal;

public class Products {

    private String ProductType;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;

    public String getProductType() {
        return ProductType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        CostPerSquareFoot = costPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        LaborCostPerSquareFoot = laborCostPerSquareFoot;
    }
}
