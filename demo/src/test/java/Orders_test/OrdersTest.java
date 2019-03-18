package Orders_test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.dao.OrderDAO;
import com.example.demo.product.dao.ProductDAO;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ResultOfOrder;
import com.example.demo.product.model.ResultOfRequest;
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
	
	@Test
	public void testInsertProductFromOrderAgain(ResultOfOrder resultOfOrder ) throws ClassNotFoundException, SQLException, UserException, URLException, ProductException, com.example.demo.product.Exception.UserException {
		OrderDAO dao = new OrderDAO();
		Date date = Date.valueOf(LocalDate.now());
		resultOfOrder.setUserId(5);
		int restaurantId = 3;
		resultOfOrder.setAddressId(3);
		resultOfOrder.setQuantity(2);
		resultOfOrder.setProductId(10);
		List<ResultOfRequest> orders = dao.listAllOrdersForUser(5);

		ProductDAO prDao = new ProductDAO();
		Product product = prDao.getProductById(10);

		assertTrue(orders.stream().filter(orders1 ->
		orders1.getProductName().equals(product.getName())).findAny().isPresent());

		dao.removeProductByOrder((int) 10l);
	}
	
	@Test
	void testDeleteProductFromOrder() throws URLException, ClassNotFoundException, SQLException, UserException, com.example.demo.product.Exception.UserException {
		
		OrderDAO dao = new OrderDAO();
		int oldCountOfUsers = dao.listAllOrdersForUser(5).size();
		
		dao.removeProductByOrder((int) 2l);
		int newCountOfUsers = dao.listAllOrdersForUser(5).size();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
	}
	

}
