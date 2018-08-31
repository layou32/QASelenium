package models;

public class Product {
	String title;
	public double price;
	public double stars;
	
	public Product(String title, double price, double stars) {
		this.title = title;
		this.price = price;
		this.stars = stars;
	}
}
