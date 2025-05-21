package edu.neu.csye7374.stocks;

import edu.neu.csye7374.StockAPI;
import edu.neu.csye7374.Tradable0;

public class MicrosoftStock extends StockAPI implements Tradable0{
    public MicrosoftStock(double price) {
	    super("Microsoft", price, "Information Technology");
	}
	
    @Override
    public double getMetric() {
        double mean = (this.prePrices.stream().mapToDouble(i -> i.doubleValue()).sum()) / prePrices.size();
        double variance = 0.0;
        for (double num : prePrices) {
            variance += Math.pow(num - mean, 2);
        }
        variance = variance / prePrices.size();
        return Math.sqrt(variance);
    }
	
	@Override
	public String toString() {
		return "Stock [name=" + this.getName() + ", price=" + this.getPrice() + ", description=" + this.getDescription() + "Metric : " + this.getMetric() + "]";
	}
}
