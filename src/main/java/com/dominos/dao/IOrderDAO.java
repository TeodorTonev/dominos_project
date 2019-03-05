package com.dominos.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeSet;

import com.dominos.exceptions.AddressException;
import com.dominos.models.Product;
import com.dominos.models.User;
import com.dominos.models.Order;

public interface IOrderDAO {

	TreeSet<Order> getOrdersForUser(long user_id) throws ClassNotFoundException, SQLException;

	HashMap<Product, Integer> getProductsForOrder(long orderId) throws SQLException;

	void insertOrderForUser(Order order) throws SQLException;

	void insertProductsFromOrder(long orderId, HashMap<Product, Integer> cart) throws SQLException;

	Order getActiveOrderForUser(User user) throws SQLException;
	
	Order getOrderById(long id) throws SQLException, ClassNotFoundException, AddressException;
	
	void deleteOrderOnAddress(long address_id);

}
