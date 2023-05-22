package se.kth.iv1350.retailStore.startup;

import java.io.IOException;

import se.kth.iv1350.reatailStore.integration.AccountingSystem;
import se.kth.iv1350.reatailStore.integration.DiscountCatalog;
import se.kth.iv1350.reatailStore.integration.InventorySystem;
import se.kth.iv1350.reatailStore.integration.Printer;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.view.View;

/**
 * Starts the application. Contains the mainmethod.
 */

public class Main {

    /**
     * The main method used to start the application
     * 
     * @param args The applicationdoes not take any command line parameters.
     */
    public static void main(String[] args)throws IOException {
        Controller contr = new Controller();
        View view = new View(contr);
        Printer printer = new Printer();
        DiscountCatalog discountCatalog = new DiscountCatalog();
        AccountingSystem accountingSystem = new AccountingSystem();
        InventorySystem inventorySystem = new InventorySystem();
        

        view.runFakeExecution();
        
    }
}
