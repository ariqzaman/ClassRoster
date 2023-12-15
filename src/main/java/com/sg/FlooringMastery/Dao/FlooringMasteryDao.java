package com.sg.FlooringMastery.Dao;

import com.sg.FlooringMastery.Dto.Order;
import com.sg.FlooringMastery.Dto.Products;
import com.sg.FlooringMastery.Dto.Tax;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface FlooringMasteryDao {

    /**
     * The remaining fields are calculated from the user entry and the tax/product information in the files.
     * Show a summary of the order once the calculations are completed and prompt the user whether they want to place the order (Y/N).
     * If yes, the data will be added to in-memory storage. If no, simply return to the main menu.
     * The system should generate an order number for the user based on the next available order # (so if there are two orders and the max order number is 4,
     * the next order number should be 5).
     *
     * @param CustomerName May not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters. "Acme, Inc." is a valid name.
     * @param State – Entered states must be checked against the tax file. If the state does not exist in the tax file, we cannot sell there. If the tax file is modified to include the state, it should be allowed without changing the application code.
     * @param ProductType Show a list of available products and pricing information to choose from. Again, if a product is added to the file it should show up in the application without a code change.
     * @param Area  The area must be a positive decimal. Minimum order size is 100 sq ft.
     * @return order that was just made
     * @throws FlooringMasteryPersistenceException
     */
    Order addOrder(String CustomerName, String State, String ProductType, BigDecimal Area)
            throws FlooringMasteryPersistenceException;

    /**
     * Edit will ask the user for a date and order number. If the order exists for that date,
     * it will ask the user for each piece of order data but display the existing data.
     * If the user enters something new, it will replace that data; if the user hits Enter without entering data, it will leave the existing data in place
     *Only certain data is allowed to be changed:
     * CustomerName
     * State
     * ProductType
     * Area
     *
     * @param OrderNumber May not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters. "Acme, Inc." is a valid name.
     * @param order May not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters. "Acme, Inc." is a valid name.
     * @return order that was just made
     * @throws FlooringMasteryPersistenceException
     */

    Order replaceOrder(int OrderNumber, Order order)
            throws FlooringMasteryPersistenceException, IOException;

    /**
     * For removing an order, the system should ask for the date and order number.
     * If it exists, the system should display the order information and prompt the user if they are sure. If yes, it should be removed from the list.
     *

     * @param OrderNumber May not be blank and is limited to characters [a-z][0-9] as well as periods and comma characters. "Acme, Inc." is a valid name.
     * @return removed order
     * @throws FlooringMasteryPersistenceException
     */
    Order removeOrder(int OrderNumber)
            throws FlooringMasteryPersistenceException;

    List<Tax> getTaxes() throws FlooringMasteryPersistenceException;

    List<Products> getProducts() throws FlooringMasteryPersistenceException;

    String getOrderDateFile(String date);

    boolean fileExists(String date);
}
