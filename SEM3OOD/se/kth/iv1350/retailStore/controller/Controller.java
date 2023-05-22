package se.kth.iv1350.retailStore.controller;

import se.kth.iv1350.retailStore.model.*;

/**
 * This is the applications controller. All calls to the model is made via this controller class.
 */

public class Controller {
    private Sale sale;

    /**
     * Starts a new sale
     */
    public void initiateSale(){
        sale = new Sale();

    }

    /**
     * Cashier has entered a new item that will be bought
     * @param itemID the identifier for the item. Used to get item info from database
     * @param quantity quantity for the item. Example: customer wants to buy 2 packages of milk
     * @return Returns a Sale object with information about the current sale
     */

    public Sale enterItem(int itemID, int quantity)throws Exception{
 
        return sale.addItem(itemID, quantity);  
    }

    /**
     * When the cashier ends the sale.
     * @return returns total price for the entire sale
     */
    public int endSale(){
        int totalPrice = sale.summarizeSale();
        return totalPrice;
    }

    /**
     * Adds an observer to the current sale
     * @param obs the observerclass to be added to the current sale
     */
    public void addObserver(SaleRevenueObserver obs){
        sale.addObserver(obs);
    }

    /**
     * The sale has ended and the custmor pays
     * @param amount. The amount paid by the customer
     * @return Returns change if the customer paid more than the total price
     */
    public int paidAmount(int amount){
        int change = sale.paidAmount(amount);
        return change;
    }

    
}
