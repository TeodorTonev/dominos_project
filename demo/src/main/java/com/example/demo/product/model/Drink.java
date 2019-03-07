package com.example.demo.product.model;

import com.example.demo.product.Exception.URLException;

public class Drink extends Product {
	private int id;
	private String description;

	public Drink() {

	}

	public Drink(long id,float price, String pictureUrl, String name) throws URLException {
			super(id, price, pictureUrl, name);
	}
	
	public long getId() {
		return this.id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}

