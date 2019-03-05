package com.example.demo.product.model;

public class Location {
	
	private int id;
	private String neighborhoodName;
	private City city;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNeighborhoodName() {
		return neighborhoodName;
	}
	
	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
}
