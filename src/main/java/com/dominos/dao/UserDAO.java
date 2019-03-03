package com.dominos.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dominos.db.DBConnection;
import com.dominos.dto.LoginDTO;
import com.dominos.models.Address;
import com.dominos.dao.IAddressDAO;
import com.dominos.models.Order;
import com.dominos.models.User;
import com.dominos.dao.OrderDAO;

@Component
public class UserDAO implements IUserDAO {

	private static final String IS_USER_EXCIST = "SELECT count(*) as count FROM user WHERE email = ? AND password = sha1(?)";
	
	private static final String HAS_SUCH_EMAIL = "SELECT count(*) as count FROM user WHERE email = ?";


	private static final String REGISTER_USER_SQL = "INSERT INTO user (firstName, lastName, email, password) VALUES (?, ?, sha1(?), ?)";

	private static final String GET_USER_BY_MAIL = "SELECT id, firstName, lastName, email, password FROM user WHERE email = ?";
	
	private static final String UPDATE_USER = "UPDATE user SET firstName = ?, lastName = ?, email = ?, password = sha1(?) WHERE id = ?;";
	
	private static final String GET_USER_BY_ID = "SELECT email AS email, firstName , lastName, password  FROM user WHERE id = ?";

	private static final String INSERT_ADDRESS_FOR_USER = "INSERT INTO address (address, id) \r\n"
			+ "					VALUES (?,?);";
	@Autowired
	private DBConnection db;

	@Autowired
	private OrderDAO od;

	@Autowired
	private AddressDAO ad;

	@Autowired
	private Connection con;
	
	
	private List<User> users;
	
	UserDAO() {
		
	}

	public void insertAddressForUser(Address address) throws SQLException {

		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(INSERT_ADDRESS_FOR_USER, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, address.getAddress());
			ps.setLong(2, address.getUser().getId());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			address.setAddress_id(rs.getLong(1));

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	public void register(User u) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(REGISTER_USER_SQL, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, u.getFirstName());
			ps.setString(2, u.getLastName());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getEmail());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			u.setId(rs.getLong(1));
			
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	public boolean hasSuchEmail(String email) throws SQLException, ClassNotFoundException {
		// Connection con = db.getConnection();
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(HAS_SUCH_EMAIL);) {

			ps.setString(1, email);
			
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count") > 0;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	public User getUser(String email) throws SQLException, ClassNotFoundException {

		/*
		  return this.users.stream()
				.filter(u -> u.getId() == id)
				.findFirst().get();
		 */ 
		//от колекцията
		
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(GET_USER_BY_MAIL);) {
			ps.setString(1, email);
			rs = ps.executeQuery();
			User u = null;
			if (!rs.next()) {
				return new User();
			}
			u = new User();
			u.setId(rs.getLong("id"));
			u.setFirstName(rs.getString("firstName"));
			u.setLastName(rs.getString("lastName"));
			u.setPassword(rs.getString("password"));
			u.setEmail(rs.getString(5));

			return u;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		// Connection con = db.getConnection();

		try (PreparedStatement stmt = con.prepareStatement(UPDATE_USER);) {
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setLong(6, user.getId());
			return stmt.executeUpdate() == 1;
		} finally {
		}
	}

	
	@Override
	public User getUserByID(long id) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;

		try (PreparedStatement stmt = con.prepareStatement(GET_USER_BY_ID);) {
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			rs.next();

			User u = new User();
			u.setId(id);
			u.setFirstName(rs.getString("firstName"));
			u.setLastName(rs.getString("lastName"));
			u.setPassword(rs.getString("password"));
			u.setEmail(rs.getString(5));

			TreeSet<Order> orders = od.getOrdersForUser(u.getId());
			HashSet<Address> addresses = ad.getAddressOfUser(u);
			u.setAddresses(addresses);
			u.setOrders(orders);
			return u;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}
	public boolean existsUser(String e_mail, String password) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(IS_USER_EXCIST);) {

			ps.setString(1, e_mail);
			ps.setString(2, password);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count") > 0;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	
	@Override
	public User login(LoginDTO user) {
		/*
		return this.users.stream()
		.filter(u -> (u.getUsername().equals(user.getEmail()) 
				&& 
				u.getPassword().equals(user.getPassword())))
		.findFirst().get();
		*/
		return null;
	}
}
