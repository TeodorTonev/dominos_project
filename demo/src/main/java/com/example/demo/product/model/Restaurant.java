package com.example.demo.product.model;


import lombok.ToString;

@ToString
public class Restaurant {
	
	private String id;
	private String loc;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLoc() {
		return loc;
	}
	
	public void setLoc(String loc) {
		this.loc = loc;
	}
}
