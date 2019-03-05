package com.dominos.models;

import com.dominos.exceptions.URLException;

public class Drink extends Product {
	private int id;

	public Drink(long id,float price, String pictureUrl, String name) throws URLException {
		super(id,price, pictureUrl, name);
	}
	
	public long getId() {
		return this.id;
		
	}
}

