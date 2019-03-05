package com.example.demo.product.dao;

import java.util.Set;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.model.*;

public interface IProductDAO {

	Product getProductById(long id) throws ProductException, URLException;

	Set<Drink> getAllDrinks() throws URLException, ProductException;

	Set<Sauce> getAllSauces() throws URLException, ProductException;

	Set<Pizza> getAllPizzas() throws URLException, ProductException;

	void addNewCustomPizza(CustomPizza customPizza);

}
