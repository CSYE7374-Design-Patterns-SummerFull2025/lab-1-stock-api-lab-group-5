package edu.neu.csye7374;

import edu.neu.csye7374.stocks.AppleStock;
import edu.neu.csye7374.stocks.MicrosoftStock;
import edu.neu.csye7374.stocks.AmazonStock;
import edu.neu.csye7374.stocks.IbmStock;

import java.util.ArrayList;
import java.util.List;

/**
 * StockMarket class implemented as a lazy Singleton
 * Manages the addition, trading, removal, and display of Stock objects
 */
public class StockMarket {

    // Singleton instance
    private static StockMarket instance;

    // List to store stocks
    private List<Tradable> stocks = new ArrayList<>();

    /**
     * Private constructor to prevent direct instantiation
     */
    private StockMarket() {
        // Initialize the instance as null
        instance = null;
    }

    /**
     * Lazy Singleton pattern implementation
     * @return The singleton instance of StockMarket
     */
    public static synchronized StockMarket getInstance() {
        if (instance == null) {
            instance = new StockMarket();
        }
        return instance;
    }

    /**
     * Add a stock to the market
     * @param stock The stock to add
     */
    public void add(Tradable stock) {
        stocks.add(stock);
        if (stock instanceof StockAPI) {
            System.out.println("Added " + ((StockAPI)stock).getName() + " to the market");
        } else {
            System.out.println("Added a stock to the market");
        }
    }

    /**
     * Remove a stock from the market
     * @param stock The stock to remove
     */
    public void remove(Tradable stock) {
        boolean removed = this.stocks.remove(stock);
        if (!removed) {
            System.out.println("Stock not found in the market");
        } else {
            if (stock instanceof StockAPI) {
                System.out.println("Removed " + ((StockAPI)stock).getName() + " from the market");
            } else {
                System.out.println("Removed a stock from the market");
            }
        }
    }

    /**
     * Trade a stock by setting a new bid
     * @param stock The stock to trade
     * @param bid The bid price
     */
    public void trade(Tradable stock, double bid) {
        if (stock instanceof StockAPI) {
            StockAPI stockAPI = (StockAPI)stock;
            double oldPrice = stockAPI.getPrice();
            double oldMetric = stockAPI.getMetric();

            // Update the stock with the new bid
            stock.setBid(bid);

            System.out.println("Trade executed for " + stockAPI.getName() + ":");
            System.out.println("  Old Price: $" + String.format("%.2f", oldPrice));
            System.out.println("  New Price: $" + String.format("%.2f", stockAPI.getPrice()));
            System.out.println("  Old Metric: " + String.format("%.2f", oldMetric));
            System.out.println("  New Metric: " + String.format("%.2f", stockAPI.getMetric()));
        } else {
            // For non-StockAPI Tradable objects
            double oldMetric = stock.getMetric();
            stock.setBid(bid);
            System.out.println("Trade executed:");
            System.out.println("  Old Metric: " + String.format("%.2f", oldMetric));
            System.out.println("  New Metric: " + String.format("%.2f", stock.getMetric()));
        }
    }

    /**
     * Display all stocks in the market
     */
    public void displayStocks() {
        if (stocks.isEmpty()) {
            System.out.println("No stocks in the market");
            return;
        }

        System.out.println("\n===== CURRENT STOCK MARKET LISTING =====");
        for (Tradable stock : this.stocks) {
            System.out.println(stock.toString());
        }
        System.out.println("=======================================\n");
    }

    /**
     * Demo method showcasing the functionality of the stock market
     */
    public static void demo() {
        System.out.println("======= STOCK MARKET DEMO =======");

        // Get the StockMarket instance
        StockMarket market = StockMarket.getInstance();

        System.out.println("\n--- INITIALIZING STOCKS ---");

        // Stock Initialization (one from each group member)
        AppleStock appleStock = new AppleStock(175.50);
        MicrosoftStock microsoftStock = new MicrosoftStock(290.20);
        AmazonStock amazonStock = new AmazonStock(125.30);
        IbmStock ibmStock = new IbmStock(131.15);

        System.out.println("\n--- ADDING STOCKS TO MARKET ---");

        // Add Stock objects to the market
        market.add(appleStock);
        market.add(microsoftStock);
        market.add(amazonStock);
        market.add(ibmStock);

        // Show initial state of the market
        market.displayStocks();

        // Define bids for each stock (6 bids per stock)
        double[] appleBids = {180.25, 178.50, 185.75, 179.30, 190.25, 187.60};
        double[] microsoftBids = {295.50, 292.30, 298.75, 290.80, 302.25, 299.50};
        double[] amazonBids = {130.40, 128.25, 132.75, 129.90, 135.60, 134.10};
        double[] ibmBids = {135.25, 130.80, 132.90, 136.75, 138.20, 135.30};

        System.out.println("\n--- TRADING STOCKS WITH MULTIPLE BIDS ---");

        // Trade AppleStock with multiple bids
        System.out.println("\nTrading AppleStock (AAPL):");
        System.out.println("--------------------------------");
        System.out.printf("%-10s %-15s %-10s%n", "Bid", "Price", "Metric");
        System.out.println("--------------------------------");
        for (double bid : appleBids) {
            double oldPrice = appleStock.getPrice();
            double oldMetric = appleStock.getMetric();
            appleStock.setBid(bid);
            System.out.printf("%-10.2f %-15.2f %-10.2f%n",
                    bid, appleStock.getPrice(), appleStock.getMetric());
        }

        // Trade MicrosoftStock with multiple bids
        System.out.println("\nTrading MicrosoftStock (MSFT):");
        System.out.println("--------------------------------");
        System.out.printf("%-10s %-15s %-10s%n", "Bid", "Price", "Metric");
        System.out.println("--------------------------------");
        for (double bid : microsoftBids) {
            double oldPrice = microsoftStock.getPrice();
            double oldMetric = microsoftStock.getMetric();
            microsoftStock.setBid(bid);
            System.out.printf("%-10.2f %-15.2f %-10.2f%n",
                    bid, microsoftStock.getPrice(), microsoftStock.getMetric());
        }

        // Trade AmazonStock with multiple bids
        System.out.println("\nTrading AmazonStock (AMZN):");
        System.out.println("--------------------------------");
        System.out.printf("%-10s %-15s %-10s%n", "Bid", "Price", "Metric");
        System.out.println("--------------------------------");
        for (double bid : amazonBids) {
            double oldPrice = amazonStock.getPrice();
            double oldMetric = amazonStock.getMetric();
            amazonStock.setBid(bid);
            System.out.printf("%-10.2f %-15.2f %-10.2f%n",
                    bid, amazonStock.getPrice(), amazonStock.getMetric());
        }

        // Trade IbmStock with multiple bids
        System.out.println("\nTrading IbmStock (IBM):");
        System.out.println("--------------------------------");
        System.out.printf("%-10s %-15s %-10s%n", "Bid", "Price", "Metric");
        System.out.println("--------------------------------");
        for (double bid : ibmBids) {
            double oldPrice = ibmStock.getPrice();
            double oldMetric = ibmStock.getMetric();
            ibmStock.setBid(bid);
            System.out.printf("%-10.2f %-15.2f %-10.2f%n",
                    bid, ibmStock.getPrice(), ibmStock.getMetric());
        }

        // Show market after trading
        market.displayStocks();

        System.out.println("\n--- DEMONSTRATING TRADE METHOD ---");

        // Demonstrate the trade method
        market.trade(appleStock, 195.50);
        market.trade(microsoftStock, 305.25);

        // Show market after more trades
        market.displayStocks();

        System.out.println("\n--- DEMONSTRATING REMOVE METHOD ---");

        // Demonstrate the remove method
        market.remove(amazonStock);

        // Show final state of the market after removal
        market.displayStocks();

        System.out.println("======= DEMO COMPLETED =======");
    }
}