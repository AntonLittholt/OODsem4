package se.kth.iv1350.retailStore.view;

import se.kth.iv1350.retailStore.model.SaleRevenueObserver;

/**
 * Display the total revenue for all sales
 */
public class TotalRevenueView implements SaleRevenueObserver{
    private int totalRevenue = 0;

    /**
     * Adds total price of last sale and displays total revenue for all sales 
     */
    @Override
    public void saleEnded(int totalPrice){
        totalRevenue += totalPrice;
        System.out.println("\nThe total revenue for all sales: " + totalRevenue + "\n");
    }
}
