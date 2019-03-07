package com.example.demo.product.model;

import com.example.demo.product.ENUM.Size;
import com.example.demo.product.Exception.URLException;
import lombok.ToString;


@ToString
public class Pizza extends Product {

	private long id;
	private Size size;

	public Pizza() {
	}


	
	public long getId() {
		return this.id;
	}
}

