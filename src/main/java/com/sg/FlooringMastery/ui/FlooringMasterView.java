package com.sg.FlooringMastery.ui;

import com.sg.FlooringMastery.Dto.Order;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class FlooringMasterView {

    private UserIO io;

    public FlooringMasterView(UserIO io) {
        this.io = io;
    }
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }
    public void displayErrorMessage(String message){
        io.print("=== ERROR ===");
        io.print(message);
    }
    public void displayEditOrderBanner(){
        io.print("=== Edit Order ===");

    }
    public void displayEditSuccessBanner(){
        io.print("Order successfully edited.");
    }
    public List<Object> editOrderInfo(){
        int orderNumber = io.readInt("Please enter the order number.");
        LocalDate date = io.readLocalDate("Please enter the order date.");
        return Arrays.asList(orderNumber, date);
    }
    public List<Object> editOrder(Order currentOrder){
        String customerName = io.readString("Please enter the customer name." + currentOrder.getCustomerName());
        String state = io.readString("Please enter the state." + currentOrder.getState());
        String productType = io.readString("Please enter the product type." + currentOrder.getProductType());
        BigDecimal area = io.readBigDecimal("Please enter the area." + currentOrder.getArea());
        if(currentOrder.getCustomerName() != customerName){
            currentOrder.setCustomerName(customerName);
        }else if(currentOrder.getState() != state){
            currentOrder.setState(state);
        }else if(currentOrder.getProductType() != productType){
            currentOrder.setProductType(productType);
        }else if(currentOrder.getArea() != area){
            currentOrder.setArea(area);
        }io.readString("Customer Name: " + currentOrder.getCustomerName() + " State: " + currentOrder.getState() +
                " Product Type: " + currentOrder.getProductType() + " Area: " + currentOrder.getArea());
        io.readString("Are you sure you want to edit this order?");
        boolean confirmEdit = false;
        if(io.readString("Are you sure you want to edit this order? (y/n)").equals("y")){
            confirmEdit = true;
        }
        return Arrays.asList(currentOrder, confirmEdit);

    }
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }
    public Order getNewOrderInfo(){
        int OrderNumber = io.readInt("Please enter the order number.");
        LocalDate OrderDate = io.readLocalDate("Please enter the order date.");
        String customerName = io.readString("Please enter the customer name.");
        String state = io.readString("Please enter the state.");
        String productType = io.readString("Please enter the product type.");
        BigDecimal area = io.readBigDecimal("Please enter the area.");
        Order currentOrder = new Order(OrderNumber);
        currentOrder.setOrderDate(OrderDate);
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(productType);
        currentOrder.setArea(area);
        return currentOrder;
    }
    public void displayAddOrderBanner(){
        io.print("=== Add Order ===");
    }
    public String getOrderNumber(){
        return io.readString("Please enter the order number.");
    }
/*
OrderNumber,CustomerName,State,TaxRate,ProductType,
Area,CostPerSquareFoot,LaborCostPerSquareFoot,
MaterialCost,LaborCost,Tax,Total,OrderDate
 */
public String getDate(){
    return io.readString("PLease enter a date in the format yyyy-mm-dd");
}
public void displayOrderList(List<Order> orderList){
    for(Order order : orderList){
        String orderInfo = String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                order.getOrderNumber(),
                order.getCustomerName(),
                order.getState(),
                order.getTaxRate().toString(),
                order.getProductType(),
                order.getArea().toString(),
                order.getCostPerSquareFoot().toString(),
                order.getLaborCostPerSquareFoot().toString(),
                order.getMaterialCost().toString(),
                order.getLaborCost().toString(),
                order.getTax().toString(),
                order.getTotal().toString());
        io.print(orderInfo);
    }io.readString("Please hit enter to continue.");
}



    public void displayRemoveOrderBanner(){
        io.print("=== Remove Order ===");
    }
    public void displayRemoveResult(boolean result){
        if(result){
            io.print("Order removed.");
        }else{
            io.print("Order not found.");
        }io.readString("Please hit enter to continue.");
    }
    public void displayCreateOrderBanner(){
        io.print("=== Create Order ===");
    }
    public void displayCreateSuccessBanner(){
        io.print("Order created.");
    }

    public void displayRemoveSuccessBanner(){
        io.readString("Order removed. Please hit enter to continue.");
    }
    public void displayAllBanner(){
        io.print("=== All Orders ===");
    }

}
