package com.example.demo.product.model;

public class Address {
	private long addressId;
	private String address;
	private long userId;

	public Address() {

	}

	public Address(long addressId, String address, long userId) {
		this.addressId = addressId;
		this.address = address;
		this.userId = userId;
	}

	public long getId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
