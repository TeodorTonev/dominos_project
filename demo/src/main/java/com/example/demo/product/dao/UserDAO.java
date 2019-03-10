package com.example.demo.product.dao;

import java.sql.*;
import java.util.Map;

import com.example.demo.product.DTO.LoginDTO;
import com.example.demo.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserDAO implements IUserDAO{

	private static final String QUERY = "SELECT * FROM dominos.users WHERE email = ? AND password = SHA1(?);";

//	@Autowired
//	private OrderDAO od;

//	@Autowired
//	private AddressDAO ad;

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insertAddressForUser(Address address) throws SQLException {
		return jdbcTemplate.update("insert into dominos.addresses_for_order value (?, ?, ?);",
				address.getId(), address.getAddress(), address.getUserId());
	}

	public int register(User user) throws SQLException, ClassNotFoundException {

		long id = user.getId();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String address = user.getAddress();
		String email = user.getEmail();
		String password = user.getPassword();

		return jdbcTemplate.update("insert into dominos.users value (?, ?, ?, ?, ?, SHA1(?));",
				id, firstName, lastName, address, email, password);
	}



	@Override
	public User getUserByID(long id) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM dominos.users WHERE id=?;";
		Map map = jdbcTemplate.queryForMap(sql, id);
		User user = new User();

		user.setId(Long.valueOf((Integer)map.get("id")));
		user.setFirstName((String)map.get("first_name"));
		user.setLastName((String)map.get("last_name"));
		user.setAddress((String)map.get("address"));
		user.setEmail((String)map.get("email"));
		user.setPassword((String)map.get("password"));

		return user;
	}

	public boolean removeUser(int id) {
		String sql = "DELETE FROM dominos.users WHERE id = ?";
		Object[] args = new Object[] {id};

		return jdbcTemplate.update(sql, args) == 1;
	}


	public User login(LoginDTO user) throws SQLException {
			Connection connection = this.jdbcTemplate.getDataSource().getConnection();
			PreparedStatement st = connection.prepareStatement(QUERY);
			st.setString(1, user.getEmail());
			st.setString(2, user.getPassword());
			ResultSet rs = st.executeQuery();

			User u = null;
			while (rs.next()) {
				u = new User(rs.getLong(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
			}

		return u;
 	}
}
