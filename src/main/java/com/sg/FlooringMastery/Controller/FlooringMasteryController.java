package com.sg.FlooringMastery.Controller;

import com.sg.FlooringMastery.Dao.FlooringMasteryDao;
import com.sg.FlooringMastery.Dao.FlooringMasteryPersistenceException;
import com.sg.FlooringMastery.Dto.Order;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayer;
import com.sg.FlooringMastery.ui.FlooringMasterView;
import com.sg.FlooringMastery.ui.UserIO;
import com.sg.FlooringMastery.ui.UserIOConsoleImpl;

import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryController {
    private UserIO io = new UserIOConsoleImpl();
    private FlooringMasteryDao dao;

    private FlooringMasteryServiceLayer service;
    private FlooringMasterView view;

    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasterView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        listOrders();
                        break;
//                case 2:
//                    addOrder();
//                    break;
//                case 3:
//                    editOrder();
//                    break;
                    case 4:
                        removeOrder();
                        break;
//                case 5:
//                    exportAllData();
                    default:
                        unknownCommand();
                }

            }
        } catch (FlooringMasteryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listOrders() throws FlooringMasteryPersistenceException {
        String orderDate = view.getDate();
        List<Order> orderList = service.getAllOrdersByDate(orderDate);
        view.displayOrderList(orderList);
    }

//    private void addOrder() {
//        view.displayAddOrderBanner();
//        Order currentOrder = view.getNewOrderInfo();
//        try{
//            service.addOrder(currentOrder);
//            view.displayCreateSuccessBanner();
//        }catch(FlooringMasteryDaoException e){
//            view.displayErrorMessage(e.getMessage());
//        }
//
//    }
//    private void editOrder() {
//        //input order number and date into list of objects
//        //setting order date and number into editorderinfo
//        //calling editorder which updates if a different value is inputted.
//        view.displayEditOrderBanner();
//        List<Object> editOrderInfo = view.editOrderInfo();
//        Order currentOrderInfo = service.getOrderByDateAndNumber((Integer) editOrderInfo.get(0), (LocalDate) editOrderInfo.get(1));
//        List<Object> editOrder = view.editOrder(currentOrderInfo);
//        if((boolean) editOrder.get(1)){
//            try{
//                service.editOrderService((Order) editOrder.get(0));
//                view.displayEditSuccessBanner();
//            }catch(FlooringMasteryDaoException e){
//                view.displayErrorMessage(e.getMessage());
//            }
//        }
//        view.printMenuAndGetSelection();
//    }
    private void removeOrder() {
        view.displayRemoveOrderBanner();
        int OrderNumber = Integer.parseInt(view.getOrderNumber());
        //move check
        if(dao.fileExists(dao.getOrderDateFile(view.getDate()))){
            try{
                service.removeOrder(OrderNumber);
                view.displayRemoveSuccessBanner();
            } catch (FlooringMasteryPersistenceException e) {
                throw new RuntimeException(e);
            }
        }

    }


//    private void exportAllData() {
//
//    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayExitBanner();
    }
}
