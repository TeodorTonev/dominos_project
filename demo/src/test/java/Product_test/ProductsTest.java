package Product_test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import com.example.demo.product.Exception.URLException;
import com.example.demo.product.dao.ProductDAO;
import com.example.demo.product.model.Product;
import org.junit.Test;

public class ProductsTest {

	@Test
	public void testAddProduct() throws SQLException, ClassNotFoundException, URLException {
		ProductDAO dao = new ProductDAO();
		int oldCountOfUsers = dao.getAllProducts().size();
		
		dao.addProductPizza(new Product((float) 6.70, "url.url"));
		int newCountOfUsers = dao.getAllProducts().size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
		
	}
	
	@Test
	void testAddProductAgain() throws ClassNotFoundException, SQLException, URLException {
		ProductDAO dao = new ProductDAO();
		Product product = new Product((float) 6.70, "url.url");
		dao.addProductPizza(product);
		
		List<String> products = dao.getAllProducts();
		assertTrue(products.stream().filter(product1 -> 
		product1.equals(product)).findAny().isPresent());
		
		dao.removeProduct((int) product.getId());
	}
	
	@Test
	void testDeleteProduct() throws URLException {
		
		ProductDAO dao = new ProductDAO();
		int oldCountOfUsers = dao.getAllProducts().size();
		
		dao.removeProduct(2);
		int newCountOfUsers = dao.getAllProducts().size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
	}
}
