package se.kth.iv1350.retailStore.model;

/**
 * A observer class that recieves notifications when a sale ends and gets the total
 *  price for the sale. Classes that wants to use this information implements this interface.
 */
public interface SaleRevenueObserver {
    
    /**
     * called when a sale is ended.
     * @param totalPrice the total price for the sale.
     */
    public void saleEnded (int totalPrice);
}
