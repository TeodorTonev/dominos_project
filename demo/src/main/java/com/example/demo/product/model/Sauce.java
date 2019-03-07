package com.example.demo.product.model;

import com.example.demo.product.Exception.URLException;

public class Sauce extends Product {
	private int id;

	public Sauce() {

	}
	
	public Sauce(long id,float price, String pictureUrl, String name) throws URLException {
		super(id, price, pictureUrl, name);
	}
	
	public long getId() {
		return this.id;
	}
}
