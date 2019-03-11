package com.example.demo.product.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map.Entry;

@Component
public class Order {
	private long id;
	private User user;
	private Restaurant restaurant;
	private HashMap<Product, Integer> products;
	private float price;
	private Address address;
	
	public Order() {
		this.products = new HashMap<Product, Integer>();
		this.price = 0;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HashMap<Product, Integer> getProducts() {
		return new HashMap<Product, Integer>(this.products);
	}

	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Address getAddres() {
		return this.address;
	}

	public void setAddres(Address address) {
		this.address = address;
	}
	
	public static double calculatePriceForCart(HashMap<Product, Integer> products) {
		double cartPrice = 0;
		for (Entry<Product, Integer> entry : products.entrySet()) {
			Product product = entry.getKey();
			int quantity = entry.getValue();
			double productPrice = product.getPrice();
			cartPrice += (productPrice * quantity);
		}
		return cartPrice;
	}
	
	public void addProduct(Product product, int quantity) {
		this.products.put(product, quantity);
	}
	
	public void removeProduct(Product product) {
		this.products.remove(product);
	}
}
