package se.kth.iv1350.retailStore.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.retailStore.model.SaleRevenueObserver;


/**
 * class for logging total revenue for all sales. 
 * Creates a new file "TotalRevenue-log.txt" and logs all information in that textfile
 */
public class TotalRevenueFileOutput implements SaleRevenueObserver {
    private static final String LOG_FILE_NAME = "TotalRevenue-log.txt";
    private PrintWriter logFile;
    private int totalrev = 0;

    /**
     * logs the sale and the total price for all sales. 
     */
    public void saleEnded(int totalPrice){
        logRevenue(totalPrice);
    }

    /**
     * Creates the logFile.
     * @throws IOException when name for file is unusable
     */
    public TotalRevenueFileOutput() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, false), true);
    }
    
    /**
     * Writes a log entry describing the total revenue for all sales.
     * 
     * @param totalPrice The total price for the last sale that was ended.
     */
    public void logRevenue(int totalPrice) {
        totalrev += totalPrice;

        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", the total price for all sales: ");
        logMsgBuilder.append(totalrev);
        logFile.println(logMsgBuilder);
        logFile.println("\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }

}
