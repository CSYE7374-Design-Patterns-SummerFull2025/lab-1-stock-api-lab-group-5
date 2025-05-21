package edu.neu.csye7374;

import edu.neu.csye7374.stocks.AppleStock;
import edu.neu.csye7374.stocks.MicrosoftStock;
import edu.neu.csye7374.stocks.AmazonStock;
import edu.neu.csye7374.stocks.IbmStock;

import java.util.ArrayList;
import java.util.List;
public class StockMarket {

    private static StockMarket instance;

    private List<StockAPI> stocks = new ArrayList<>();

    private StockMarket() {
        instance = null;
    }

    // lazy Singleton pattern
    public static synchronized StockMarket getInstance() {

        if (instance == null) {
            instance = new StockMarket();
        }

        return instance;
    }
    public void add(StockAPI stock) {
        stocks.add(stock);
    }

    public void remove(StockAPI s) {

        boolean removed = this.stocks.remove(s);

        if (!removed) {
            System.out.println("stock missing");
        }
    }

    public void displayStocks() {
        for (StockAPI s : this.stocks) {
            System.out.println(s.toString());
        }
    }

    public static void demo() {

        // Stock Initialization
        AppleStock appleStock = new AppleStock(65);
        MicrosoftStock microsoftStock = new MicrosoftStock(40);
        AmazonStock amazonStock = new AmazonStock(60);
        IbmStock ibmStock = new IbmStock(100);

        // Add Stock
        StockMarket.getInstance().add(appleStock);
        StockMarket.getInstance().add(microsoftStock);
        StockMarket.getInstance().add(amazonStock);
        StockMarket.getInstance().add(ibmStock);

        // Display the metrics for each stock
        displayBidsMetrics(new double[] {20,30,50,60,75,80},appleStock);
        displayBidsMetrics(new double[] {40,15,20,25,50,35},microsoftStock);
        displayBidsMetrics(new double[] {60,50,55,65,85,90},amazonStock);
        displayBidsMetrics(new double[] {70,40,60,70,85,90},ibmStock);

        // Displaying all Stocks
        StockMarket.getInstance().displayStocks();
    }

    public static void displayBidsMetrics(double[] bids, StockAPI stock){
        System.out.println("\nStock: " + stock.getName());
        System.out.println("--------------------------------");
        System.out.printf("%-10s %-15s %-10s%n", "Bid", "New Price", "Metric");
        System.out.println("--------------------------------");

        for(double bid : bids) {
            stock.setBid(bid);
            double price = stock.getPrice();
            double metric = stock.getMetric();
            System.out.printf("%-10.2f %-15.2f %-10.2f%n", bid, price, metric);
        }

    }
}
