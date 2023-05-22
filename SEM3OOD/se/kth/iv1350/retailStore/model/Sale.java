package se.kth.iv1350.retailStore.model;

import java.time.LocalTime;
import java.util.ArrayList;
import se.kth.iv1350.reatailStore.integration.AccountingSystem;
import se.kth.iv1350.reatailStore.integration.InventorySystem;
import se.kth.iv1350.reatailStore.integration.ItemDTO;

/**
 * One single sale with one customer and one payment.
 */
public class Sale {
    private ArrayList<SaleRevenueObserver> saleRevenueObservers = new ArrayList<>();
    private LocalTime saleTime;
    private Receipt receipt;
    private Cart cart;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private int runningTotal;
    private int totalVAT;

    /**
     * creates a new instance and saves time of sale
     */
    public Sale(){
        saleTime = LocalTime.now();
        cart = new Cart();
        inventorySystem = new InventorySystem();
        accountingSystem = new AccountingSystem();
        
    }


    /**
     * A new item is added to the sale that the customer will buy.
     * Uppdates cart, running total and get item info.
     * @param itemID Identifier for the item. Used to search in database
     * @param quantity. Quantity of the item 
     * @return Returns a sale object for the view so that customer and cashier gets sale info. 
     *          If <code>null</code> is returned then the item was not identified.
     */
    public Sale addItem (int itemID, int quantity) throws ItemNotFoundException, DataBaseFailureException{
        
        ItemDTO itemDTO = inventorySystem.fetchItemInfo(itemID);
        
        this.cart = cart.addToCart(quantity, itemDTO);
        this.runningTotal = getTotalPriceOfSale();

        return this;
    }

    /**
     * Summarizes important sale information and notifies observers when a sale is ended.
     * @return total price for sale.
     */
    public int summarizeSale(){
        this.totalVAT = calculateTotalVAT();
        int totalPrice = getTotalPriceOfSale();

        notifyObservers(totalPrice);
        return totalPrice;
    } 

    /**
     * Adds a observer class to the current sale.
     * @param obs The observer class that is added.
     */
    public void addObserver(SaleRevenueObserver obs){
        saleRevenueObservers.add(obs);
    }

    private void notifyObservers(int totalPrice){
        for(SaleRevenueObserver obs: saleRevenueObservers){
            obs.saleEnded(totalPrice);
        }
    }

    private int calculateTotalVAT(){ 
        int totalVAT = 0;
        for (Item item : cart.getItemsInCart()){
            
            totalVAT += (item.getQuantity() * item.getItemPrice()) * (item.getItemVat()/100.0);
        }
        return totalVAT;
    }

    private int getTotalPriceOfSale(){
        int totalPrice = 0;

        for (int i = 0; i < cart.cart.size(); i++) {
            Item item = cart.cart.get(i);
            int quantity = item.getQuantity();
            int vat = item.getItemVat();
            int price = item.getItemPrice();
            totalPrice += totalPriceForItem(quantity, price, vat);

        }
        this.runningTotal = totalPrice;  
        return totalPrice;
    }

    private int totalPriceForItem(int quantity, int price, int vat){
        return quantity*price*(1+vat/100);
    }
    /**
     * Calculates change and generates a receipt. Also logs the sale and uppdates inventory.
     * @param amount The amount paid by the customer.
     * @return returns the change 
     */
    public int paidAmount(int amount){
        int change = calculateChange(amount);
        receipt = new Receipt(this, amount, change);
        receipt.generateAndPrintReceipt(receipt);
        accountingSystem.logSale(this);
        inventorySystem.uppdateInventory(cart);
        return change;
    }

    private int calculateChange(int amount){
        return amount-runningTotal; 
    }

    /**
     * fetches the time of sale
     * @return Returns the sale time.
     */
    public LocalTime getSaleTime(){
        return saleTime;
    }

    /**
     * fetches the cart object
     * @return Returns cart.
     */
    public Cart getCart(){
        return cart;
    }

    /**
     * Gets the running total for the sale
     * @return total price for sale
     */
    public int getRunningTotal(){
        return runningTotal;
    }

    /**
     * Get the total VAT for the sale
     * @return Returns Total VAT
     */
    public int getTotalVat(){
        return totalVAT;
    }

    /**
     * Creates a string for the Receipt that displays all sale information.
     * @param sale. The Sale objects that represents the current sale
     * @return String with all sale infromation.
     */
    public String generateSaleinfo(Sale sale){

        String saleInfo = "Name:  Quantity:  Price: \n" +
                          "-------------------------\n";
        ArrayList<Item> cartItems = cart.getItemsInCart();
        for (int i = 0; i < cartItems.size(); i++) {
            Item item = cartItems.get(i);
            saleInfo += item.getItemName() + "    " + item.getQuantity() + "         " + item.getItemPrice() + "\n";
        }
        saleInfo += "-------------------------";
        return saleInfo;
    }

}