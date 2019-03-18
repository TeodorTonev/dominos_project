package User_test;

import static org.junit.Assert.*;

import java.util.List;
import java.sql.SQLException;
import java.util.Set;

import com.example.demo.product.dao.UserDAO;
import com.example.demo.product.model.Address;
import com.example.demo.product.model.User;
import org.junit.Test;

public class UsersTest {

	@Test
	void testAddUser() throws SQLException, ClassNotFoundException {
		UserDAO dao = new UserDAO();

		int oldCountOfUsers = dao.countAllUsers();
		
		dao.register(new User(1, "Ivan", "Georgiev", "ul.Vishneva N30", "ivanGeorgiev@abv.bg", "pecheniq"));
		int newCountOfUsers = dao.countAllUsers();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
	}
	
	@Test
	void testAddUserAgain() throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		User user = new User(1, "Ivan", "Georgiev", "ul.Vishneva N30", "ivanGeorgiev@abv.bg", "pecheniq");
		dao.register(user);
		
		List<String> users = dao.listAllUsers();
		assertTrue(users.stream().filter(user1 ->
		user1.equals(user.getEmail()))
				.findAny().isPresent());
		
		dao.removeUser((int) user.getId());
	}
	
	@Test
	void testAddAddress() throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		Address address = new Address(50, "гр.София, ул.Милин Камък N8", 6);
		dao.insertAddressForUser(address);
		
		List<String> addresses = dao.getAllAdressesForUser();
		
		assertTrue(addresses.stream().filter(address1 -> 
		address1.equals(address.getAddress()))
				.findAny().isPresent());
		
		//dao.removeAddressForUser();
	}
	
	@Test
	void testDeleteUser() throws SQLException {
		UserDAO dao = new UserDAO();
		int oldCountOfUsers = dao.countAllUsers();
		
		dao.removeUser(2);
		int newCountOfUsers = dao.countAllUsers();
	
		assertNotSame(oldCountOfUsers, newCountOfUsers);
	}
	
	void testDeleteUserAgain() {
		
		UserDAO dao = new UserDAO();
		dao.removeUser(3);
		assertNull(3);
	}
	
	
}
