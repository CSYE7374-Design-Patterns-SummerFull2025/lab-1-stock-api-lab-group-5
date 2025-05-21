package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class StockAPI implements Tradable {

	private String name;

	private double price;

	private String description;
	
	private int metric;

	protected List<Double> prePrices = new ArrayList<>();

	public StockAPI() {
		super();
	}

	public StockAPI(String name, double price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		init(price);
	}

	private void init(double price) {
		prePrices.add(price);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Stock : name=" + name + ", price=" + price + ", description=" + description + "Metric : " + metric + "";
	}

	@Override
	public double getMetric() {
		double mean = (prePrices.stream().mapToDouble(i -> i.doubleValue()).sum()) / prePrices.size();
		double dev = 0.0;
		for (double num : prePrices) {
			dev += mean - num;
		}
		dev = dev / prePrices.size();
		this.metric = dev > 0.0 ? 1 : -1;
		return this.metric;
	}
	
	@Override
	public void setBid(double bid) {
	    prePrices.add(bid);
	    double newPrice = (prePrices.stream().mapToDouble(i -> i.doubleValue()).sum())/ prePrices.size();
	    this.updatePrice(newPrice);
	}

	public void updatePrice(double price) {
			this.setPrice(price);
	}

}