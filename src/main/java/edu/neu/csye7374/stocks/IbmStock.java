package edu.neu.csye7374.stocks;

import edu.neu.csye7374.StockAPI;
import edu.neu.csye7374.Tradable0;

public class IbmStock extends StockAPI implements Tradable0 {

    public IbmStock(double price) {
        super("IBM", price, "Software and Cloud Services");
    }

    @Override
    public double getMetric() {
        // Logic: weighted average where the most recent price gets more weight
        double totalWeight = 0.0;
        double weightedSum = 0.0;
        int weight = 1;

        for (int i = prePrices.size() - 1; i >= 0; i--) {
            weightedSum += prePrices.get(i) * weight;
            totalWeight += weight;
            weight++;
        }

        return weightedSum / totalWeight;
    }

    @Override
    public String toString() {
        return "Stock [name=" + this.getName() + ", price=" + this.getPrice() + ", description=" + this.getDescription() + ", Metric: " + this.getMetric() + "]";
    }
}