package Orders_test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.dao.OrderDAO;
import org.junit.Test;
import org.omg.CORBA.UserException;

public class OrdersTest {

	@Test
	public void testInsertProductFromOrder() throws ClassNotFoundException, SQLException, UserException, com.example.demo.product.Exception.UserException {
		OrderDAO dao = new OrderDAO();
		int oldCountOfUsers = dao.listAllOrdersForUser(5).size();
		
		int newCountOfUsers = dao.listAllOrdersForUser(5).size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
			
	}
	
//	@Test
//	public void testInsertProductFromOrderAgain() throws ClassNotFoundException, SQLException, UserException, URLException, ProductException {
//		OrderDAO dao = new OrderDAO();
//		dao.insertProductsFromOrder(10, 3, 4);
//
//		List<ResultOfRequest> orders = dao.listAllOrdersForUser(5);
//
//		ProductDAO prDao = new ProductDAO();
//		Product product = prDao.getProductById(10);
//
//		assertTrue(orders.stream().filter(orders1 ->
//		orders1.getProductName().equals(product.getName())).findAny().isPresent());
//
//		dao.removeProductByOrder(10l);
//	}
	
	@Test
	void testDeleteProductFromOrder() throws URLException, ClassNotFoundException, SQLException, UserException, com.example.demo.product.Exception.UserException {
		
		OrderDAO dao = new OrderDAO();
		int oldCountOfUsers = dao.listAllOrdersForUser(5).size();
		
		dao.removeProductByOrder((int) 2l);
		int newCountOfUsers = dao.listAllOrdersForUser(5).size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
	}
	

}
