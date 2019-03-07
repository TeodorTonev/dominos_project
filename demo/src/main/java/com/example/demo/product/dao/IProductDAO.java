package com.example.demo.product.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.model.*;

public interface IProductDAO {

	Product getProductById(long id) throws ProductException, URLException, SQLException;

	List<Drink> getAllDrinks() throws URLException, ProductException, SQLException;

	List<Sauce> getAllSauces() throws URLException, ProductException, SQLException;

	List<Pizza> getAllPizzas() throws URLException, ProductException, SQLException;

	void addNewCustomPizza(CustomPizza customPizza) throws SQLException;

}
