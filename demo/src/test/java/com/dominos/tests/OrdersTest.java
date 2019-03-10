package com.dominos.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.dominos.dao.OrderDAO;
import com.dominos.dao.ProductDAO;
import com.dominos.exceptions.ProductException;
import com.dominos.exceptions.URLException;
import com.dominos.exceptions.UserException;
import com.dominos.models.Product;
import com.dominos.models.ResultOfRequest;

public class OrdersTest {

	@Test
	public void testInsertProductFromOrder() throws ClassNotFoundException, SQLException, UserException {
		OrderDAO dao = new OrderDAO();
		int oldCountOfUsers = dao.listAllOrdersForUser(5).size();
		
		dao.insertProductsFromOrder(10, 3, 4);
		
		int newCountOfUsers = dao.listAllOrdersForUser(5).size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
			
	}
	
	@Test
	public void testInsertProductFromOrderAgain() throws ClassNotFoundException, SQLException, UserException, URLException, ProductException {
		OrderDAO dao = new OrderDAO();
		dao.insertProductsFromOrder(10, 3, 4);
		
		List<ResultOfRequest> orders = dao.listAllOrdersForUser(5);
		
		ProductDAO prDao = new ProductDAO();
		Product product = prDao.getProductById(10);
		
		assertTrue(orders.stream().filter(orders1 -> 
		orders1.getProductName().equals(product.getName())).findAny().isPresent());
		
		dao.removeProductByOrder(10l);
	}
	
	@Test
	void testDeleteProductFromOrder() throws URLException, ClassNotFoundException, SQLException, UserException {
		
		OrderDAO dao = new OrderDAO();
		int oldCountOfUsers = dao.listAllOrdersForUser(5).size();
		
		dao.removeProductByOrder(2l);
		int newCountOfUsers = dao.listAllOrdersForUser(5).size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
	}
	

}
