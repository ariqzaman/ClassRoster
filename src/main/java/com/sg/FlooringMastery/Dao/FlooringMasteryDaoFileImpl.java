package com.sg.FlooringMastery.Dao;

import com.sg.FlooringMastery.Dto.Order;
import com.sg.FlooringMastery.Dto.Products;
import com.sg.FlooringMastery.Dto.Tax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

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

    public List<Tax> getTaxes() throws FlooringMasteryPersistenceException {
        List<Tax> taxes = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Taxes.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load Taxes into memory.", e);
        }
        String currentLine;
        Tax currentTax;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);
            taxes.add(currentTax);
        }
        scanner.close();
        return taxes;
    }

    private Tax unmarshallTax(String taxAsText) {
        String[] taxTokens = taxAsText.split(",");
        String stateAbbreviation = taxTokens[0];
        String stateName = taxTokens[1];
        String taxRate = taxTokens[2];
        Tax taxFromFile = new Tax();
        taxFromFile.setStateAbbreviation(stateAbbreviation);
        taxFromFile.setStateName(stateName);
        taxFromFile.setTaxRate(new BigDecimal(taxRate));
        return taxFromFile;
    }

    public List<Products> getProducts() throws FlooringMasteryPersistenceException {
        List<Products> products = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Products.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load Products into memory.", e);
        }
        String currentLine;
        Products currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            products.add(currentProduct);
        }
        scanner.close();
        return products;
    }

    private Products unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(",");
        String productType = productTokens[0];
        String costPerSquareFoot = productTokens[1];
        String laborCostPerSquareFoot = productTokens[2];
        Products productFromFile = new Products();
        productFromFile.setProductType(productType);
        productFromFile.setCostPerSquareFoot(new BigDecimal(costPerSquareFoot));
        productFromFile.setLaborCostPerSquareFoot(new BigDecimal(laborCostPerSquareFoot));
        return productFromFile;
    }
}
