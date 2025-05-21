package edu.neu.csye7374.stocks;

import edu.neu.csye7374.StockAPI;
import edu.neu.csye7374.Tradable0;

public class AppleStock extends StockAPI implements Tradable0 {
	
	public AppleStock(double price) {
	    super("Apple", price, "Tech ");
	}
	
	@Override
	public double getMetric() {
		double mean = (this.prePrices.stream().mapToDouble(i -> i.doubleValue()).sum()) / prePrices.size();
		double dev = 0.0;
		for (double num : prePrices) {
			dev += mean*2 - num/3;
		}
		dev = dev / prePrices.size();
		return dev;
	}
	
	@Override
	public String toString() {
		return "Stock [name=" + this.getName() + ", price=" + this.getPrice() + ", description=" + this.getDescription() + "Metric : " + this.getMetric() + "]";
	}
}
