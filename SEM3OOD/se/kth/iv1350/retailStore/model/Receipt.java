package se.kth.iv1350.retailStore.model;

import java.time.LocalTime;
import se.kth.iv1350.reatailStore.integration.*;

/**
 * Represents one receipt which contains information about one sale.
 */
public class Receipt {
    private Cart cart;
    private Sale sale;
    private int totalPrice;
    private int totalVAT;
    private int amountPaid;
    private int change;
    private Printer printer;
    private LocalTime time;

    /**
     * Constructor for the receipt. The receipt object gets all information about the sale.
     * @param sale. The sale for wich the receipt will document
     * @param amountPaid. The amount paid by the customer
     * @param change. The change that the customer gets back
     */
    public Receipt(Sale sale, int amountPaid, int change){
        printer = new Printer();
        this.time = sale.getSaleTime();
        this.cart = sale.getCart();
        this.sale = sale;
        this.totalPrice = sale.getRunningTotal();
        this.totalVAT = sale.getTotalVat();
        this.amountPaid = amountPaid;
        this.change = change;
    }

    /**
     * Generates a String that will be the actual receipt. Then tells the printer to print it.
     * @param receipt. The receipt that will be printed.
     */
    public void generateAndPrintReceipt(Receipt receipt){
        String generatedReceipt = "RECEIPT"+"\n"+"Date and time: " + time+"\n\n"+
        sale.generateSaleinfo(sale)+"\n"+
        "total VAT: " + sale.getTotalVat() +"\n"+
        "total price: " + sale.getRunningTotal()+"\n"+
        "Amount Paid: " + amountPaid +"\n"+
        "Change: " + change
        ;

        printer.print(generatedReceipt);
    }

}
