package com.dominos.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.dominos.dao.ProductDAO;
import com.dominos.dao.UserDAO;
import com.dominos.exceptions.URLException;
import com.dominos.models.Product;
import com.dominos.models.User;

public class ProductsTest {

	@Test
	public void testAddProduct() throws SQLException, ClassNotFoundException, URLException{
		ProductDAO dao = new ProductDAO();
		int oldCountOfUsers = dao.getAllProducts().size();
		
		dao.addProduct(new Product(155, "Препечена пица", "Моцарела, Доматен сос, кашкавал", 6.70, "Голяма(8 парчета)", "url.url", 2));
		int newCountOfUsers = dao.getAllProducts().size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
		
	}
	
	@Test
	void testAddProductAgain() throws ClassNotFoundException, SQLException, URLException {
		ProductDAO dao = new ProductDAO();
		Product product = new Product(155, "Препечена пица", "Моцарела, Доматен сос, кашкавал", 6.70, "Голяма(8 парчета)", "url.url", 2);
		dao.addProduct(product);
		
		List<String> products = dao.getAllProducts();
		assertTrue(products.stream().filter(product1 -> 
		product1.equals(product)).findAny().isPresent());
		
		dao.removeProduct(product.getId());
	}
	
	@Test
	void testDeleteProduct() {
		
	}

}
