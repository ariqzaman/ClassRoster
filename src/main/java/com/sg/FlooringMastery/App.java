package com.sg.FlooringMastery;

import com.sg.FlooringMastery.Controller.FlooringMasteryController;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayer;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.FlooringMastery.ui.FlooringMasterView;
import com.sg.FlooringMastery.ui.UserIO;
import com.sg.FlooringMastery.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        FlooringMasteryServiceLayer serviceLayer = new FlooringMasteryServiceLayerImpl();
        UserIO userIO = new UserIOConsoleImpl();
        FlooringMasterView view = new FlooringMasterView(userIO);
        FlooringMasteryController controller = new FlooringMasteryController(serviceLayer, view);
        controller.run();
    }
}
