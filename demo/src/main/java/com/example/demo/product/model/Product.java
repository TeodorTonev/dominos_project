package com.example.demo.product.model;

import java.math.BigDecimal;

import com.example.demo.product.ENUM.TypeProduct;
import com.example.demo.product.Exception.URLException;

public class Product {
	public static final int MAX_INDEX_OF_NUMBER_TYPE_PRODUCT = 10;
	protected long id;
	private double price;
	private String pictureUrl;
	private String name;
	private String description;
	private int tp;
	private String sizeP;
	
	public Product() {
	}

	public Product(float price, String pictureUrl) throws URLException {
		this.price = price;
//		if (pictureUrl!=null && pictureUrl.trim().length()>0)
			this.pictureUrl = pictureUrl;
//		else {
//			throw new URLException("Invalid URL!!");
//		}
	}

	public Product(long id, float price, String pictureUrl, String name) throws URLException {
		this(price, pictureUrl);
		if (name!=null && name.trim().length()>0) {
			this.name=name;
		}
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public void setTypeProduct(int tp) {
		if (tp < 1 && tp > MAX_INDEX_OF_NUMBER_TYPE_PRODUCT) {
			System.out.println("Invalid product type!");
		}
		else {
			this.tp = tp;
		}
	}

	public double getPrice() {
		BigDecimal bd = new BigDecimal(this.price);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public String getDescription() {
		return this.description;
	}

	public void updatePrice(float price) {
		this.price = price;
	}

	public int getTypeProduct() {
		return this.tp;
	}

	public String getName() {
		return name;
	}

	public String getSizeP() {
		return sizeP;
	}

	public void setSizeP(String size) {
		this.sizeP = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
}


