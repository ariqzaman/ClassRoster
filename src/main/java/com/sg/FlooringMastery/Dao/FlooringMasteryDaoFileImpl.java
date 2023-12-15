package com.sg.FlooringMastery.Dao;

import com.sg.FlooringMastery.Dto.Order;
import com.sg.FlooringMastery.Dto.Products;
import com.sg.FlooringMastery.Dto.Tax;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayerImpl;

import java.io.*;
import java.nio.file.Path;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {
    private final String ROSTER_FILE;
    private int autoIncrement = 1;

    public static final String DELIMITER = ",";

    public FlooringMasteryDaoFileImpl(String rosterTextFile){
        ROSTER_FILE = rosterTextFile;
    }
    Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(String CustomerName, String State, String ProductType, BigDecimal Area) throws FlooringMasteryPersistenceException {
        return null;
    }

    @Override
    public Order replaceOrder(int OrderNumber, Order order) throws FlooringMasteryPersistenceException, IOException {
        return orders.put(OrderNumber, order);
    }
    @Override
    public String getOrderDateFile(String date) {
        //input example 02/07/1999
        String fileName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate ld = LocalDate.parse(date, formatter);
        String formatted = ld.format(formatter);
        fileName = "Orders_" + formatted + ".txt";
        return fileName;
        //----------------- Temp way to get all files in folder designated by path. Will add it to filesinfolder list,
//        List<File> filesInFolder = Files.walk(Paths.get("C:/Users/Ariq/Desktop/quizz/SampleFileData/Orders"))
//                .filter(Files::isRegularFile)
//                .map(Path::toFile)
//                .collect(Collectors.toList());
        //--------------------

    }

    @Override
    public Order removeOrder(int OrderNumber) throws FlooringMasteryPersistenceException {
        loadRoster();
        Order removedOrder = orders.remove(OrderNumber);
        writeRoster();
        return removedOrder;
    }

    @Override
    public List<Tax> getTaxes() throws FlooringMasteryPersistenceException {
        List<Tax> taxes = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Data/Taxes.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load Taxes into memory.", e);
        }
        String currentLine;
        Tax currentTax;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);
            taxes.add(currentTax);
        }
        scanner.close();
        return taxes;
    }

    @Override
    public List<Products> getProducts() throws FlooringMasteryPersistenceException {
        List<Products> products = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader("Data/Products.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load Products into memory.", e);
        }
        String currentLine;
        Products currentProduct;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            products.add(currentProduct);
        }
        scanner.close();
        return products;
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


    //---------------------------------------------------------------------------------------

    private Order unmarshallOrder(String OrderAsText){

        String[] orderTokens = OrderAsText.split(DELIMITER);

        // Given the pattern above, the order number is in index 0 of the array.
        String orderNumber = orderTokens[0];


        Order orderFromFile = new Order(Integer.parseInt(orderNumber));


        // Index 1 - customerName
        orderFromFile.setCustomerName(orderTokens[1]);

        // Index 2 - State
        orderFromFile.setState(orderTokens[2]);

        // Index 3 - TaxRate
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));



        // Index 4 - ProductType
        orderFromFile.setProductType(orderTokens[4]);

        // Index 5 - Area
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));

        // Index 6 - CostPerSquareFoot
        orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));

        // Index 7 - LaborCostPerSquareFoot
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));

//        // Index 8 - MaterialCost
//        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
//
//        // Index 9 - LaborCost
//        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
//
//        // Index 10 - Tax
//        orderFromFile.setTax(new BigDecimal(orderTokens[10]));
//
//        // Index 11 - Total
//        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));

        // We have now created an order! Return it!
        return orderFromFile;
    }




    //LOAD
    private void loadRoster() throws FlooringMasteryPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentOrder holds the most recent order unmarshalled
        Order currentOrder;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // order object by calling the unmarshallOrder method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into an order
            currentOrder = unmarshallOrder(currentLine);

            // We are going to use the order num as the map key for our student object.
            // Put currentOrder into the map using OrderNumber as the key
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        // close scanner
        scanner.close();
    }




    private String marshallOrder(Order aOrder){

        // Start with the order num, since that's supposed to be first.
        String orderAsText = aOrder.getOrderNumber() + DELIMITER;

        // add the rest of the properties in the correct order:

        // getCustomerName
        orderAsText += aOrder.getCustomerName() + DELIMITER;

        // getState
        orderAsText += aOrder.getState() + DELIMITER;

        // getTaxRate
        orderAsText += aOrder.getTaxRate() + DELIMITER;

        // getProductType
        orderAsText += aOrder.getProductType() + DELIMITER;

        // getArea
        orderAsText += aOrder.getArea() + DELIMITER;

        // getCostPerSquareFoot
        orderAsText += aOrder.getCostPerSquareFoot() + DELIMITER;

        // getLaborCostPerSquareFoot
        orderAsText += aOrder.getLaborCostPerSquareFoot() + DELIMITER;

        // getMaterialCost
        orderAsText += aOrder.getMaterialCost() + DELIMITER;

        // getLaborCost
        orderAsText += aOrder.getLaborCost() + DELIMITER;

        // getTax
        orderAsText += aOrder.getTax() + DELIMITER;

        // getTotal - don't forget to skip the DELIMITER here.
        orderAsText += aOrder.getTotal();

        // We have now turned a student to text! Return it!
        return orderAsText;
    }

    //WRITE
    /**
     * Writes all orders in the roster out to a ROSTER_FILE.
     *
     * @throws FlooringMasteryPersistenceException if an error occurs writing to the file
     */
    private void writeRoster() throws FlooringMasteryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save order data.", e);
        }

        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String orderAsText;
        loadRoster();
        List<Order> orderList = new ArrayList(orders.values());
        for (Order currentOrder : orderList) {
            // turn an order into a String
            orderAsText = marshallOrder(currentOrder);
            // write the order object to the file
            out.println(orderAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }


}
