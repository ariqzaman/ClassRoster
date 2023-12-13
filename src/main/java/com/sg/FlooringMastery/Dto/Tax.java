package com.sg.FlooringMastery.Dto;

import java.math.BigDecimal;

public class Tax {

    private String StateAbbreviation;
    private String StateName;
    private BigDecimal TaxRate;

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        TaxRate = taxRate;
    }

    public String getStateAbbreviation() {
        return StateAbbreviation;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        StateAbbreviation = stateAbbreviation;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

}
