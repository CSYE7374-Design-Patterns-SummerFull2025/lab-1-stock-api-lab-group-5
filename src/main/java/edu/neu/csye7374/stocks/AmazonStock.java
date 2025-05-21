package edu.neu.csye7374.stocks;

import edu.neu.csye7374.StockAPI;
import edu.neu.csye7374.Tradable0;

public class AmazonStock extends StockAPI implements Tradable0 {

    public AmazonStock(double price) {
        super("Amazon", price, "E commerce");
    }
    @Override
    public double getMetric() {
        double weightedSum = 0.0;
        int weight = 1;
        int totalWeight = 0;

        for (double price : prePrices) {
            weightedSum += price * weight;
            totalWeight += weight;
            weight++;
        }
        double weightedAverage = weightedSum / totalWeight;
        return weightedAverage;
    }

    @Override
    public String toString() {
        return "Stock [name=" + this.getName() + ", price=" + this.getPrice() + ", description=" + this.getDescription() + "Metric : " + this.getMetric() + "]";
    }
}
