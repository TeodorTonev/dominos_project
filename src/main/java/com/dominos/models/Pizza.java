package com.dominos.models;

import com.dominos.enums.Size;
import com.dominos.exceptions.URLException;

public class Pizza extends Product {

	private int id;
	//private Dough dough;
	private Size size;
	private String ingradients;;

	public Pizza(long id,float price, String pictureUrl, String name) throws URLException {
		super(id, price, pictureUrl, name);
	}

	public Pizza(long id,float price, String pictureUrl, String name, /*Dough dough,*/ Size size, String ingradients)
			throws URLException {
		super(id, price, pictureUrl, name);
		//this.dough = dough;
		this.size = size;
		this.ingradients = ingradients;
	}

	@Override
	public String toString() {
		return "Pizza [size=" + size + ", ingradients=" + ingradients + ", getPrice()="
				+ getPrice() + "]";
	}

	public String getIngradients() {
		return ingradients;
	}


	public void setIngradients(String ingradients) {
		this.ingradients = ingradients;
	}
	
	public long getId() {
		return this.id;
	}
}

