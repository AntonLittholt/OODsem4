package se.kth.iv1350.retailStore.view;

import java.util.ArrayList;
import java.io.IOException;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.model.*;
import se.kth.iv1350.retailStore.util.*;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution
 *  with calls to all system operations in the controller.
 */
public class View {
    private Controller contr;
    private LogHandler logger;
    private TotalRevenueFileOutput trFileOutput;
    private TotalRevenueView trView;


    /**
     * creates a new instance that uses the specified controller for all calls to other layers. 
     * 
     * @param contr Controller used for all calls to other layers.
     */
    public View(Controller contr)throws IOException{
        this.contr = contr;
        this.logger = new LogHandler();
        trView = new TotalRevenueView();
        trFileOutput = new TotalRevenueFileOutput();
    }

    /**
    * Performs a fake sale by calling all system operations in controller.
    */
    public void runFakeExecution()throws IOException{

        contr.initiateSale();
        contr.addObserver(trView);
        contr.addObserver(trFileOutput);
        
        
        System.out.println("A new Sale has begun");

        enterItemToSale(0, 3);  //butter
        enterItemToSale(1, 2);  //trocadero
        enterItemToSale(5, 2);  //no matching item ID
        enterItemToSale(2, 1);  //milk
        enterItemToSale(10, 7); //database failure       
        
        int totalPrice = contr.endSale();
        int change = contr.paidAmount(500);

        contr.initiateSale();                   //new sale begins 
        contr.addObserver(trView);
        contr.addObserver(trFileOutput);
        System.out.println("\nA new Sale has begun");

        enterItemToSale(0, 3);  //butter
        enterItemToSale(1, 2);  //trocadero
        int totalPriceForSale = contr.endSale();
        int changeForSale = contr.paidAmount(500);

    }

    private void enterItemToSale(int itemID, int quantity){
        try{
            printSaleinfo(contr.enterItem(itemID, quantity));
        }
        catch(Exception exc){
            System.out.println("\nERROR: "+exc.getMessage()+"\n");
            logger.logException(exc);
        }

    }

    private void printSaleinfo(Sale saleInfo){

        if (saleInfo == null) {
            System.out.println("ERROR: ITEM COULD NOT BE IDENTIFIED\n");
            return;
        }
   
        System.out.println("Current Sale:");
        System.out.println("Description:  Price: ");
        System.out.println("--------------------------");
        Cart cart = saleInfo.getCart();
        ArrayList<Item> cartItems = cart.getItemsInCart();
        for (int i = 0; i < cartItems.size(); i++) {
            Item item = cartItems.get(i);
            System.out.println(item.getItemName() + "           " + item.getItemPrice()+"x"+item.getQuantity());
        }
   
        System.out.println("Running Total: " + saleInfo.getRunningTotal());
        System.out.println("--------------------------\n");
   
    }
}
