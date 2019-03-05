package com.dominos.dao;

import java.util.Set;

import com.dominos.exceptions.ProductException;
import com.dominos.exceptions.URLException;
import com.dominos.models.Product;
import com.dominos.models.Drink;
import com.dominos.models.CustomPizza;
import com.dominos.models.Pizza;
import com.dominos.models.Sauce;

public interface IProductDAO {

	Product getProductById(long id) throws ProductException, URLException;

	Set<Drink> getAllDrinks() throws URLException, ProductException;

	Set<Sauce> getAllSauces() throws URLException, ProductException;

	Set<Pizza> getAllPizzas() throws URLException, ProductException;

	void addNewCustomPizza(CustomPizza customPizza);

}
