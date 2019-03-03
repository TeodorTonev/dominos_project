package com.dominos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dominos.db.DBConnection;
import com.dominos.exceptions.AddressException;
import com.dominos.models.Order;
import com.dominos.models.Product;
import com.dominos.dao.ProductDAO;
import com.dominos.models.User;
import com.dominos.dao.UserDAO;
import com.dominos.models.Address;

@Component
public class AddressDAO implements IAddressDAO {

	private static final String GET_ALL_ADDRESSES_OF_USER = "";

	private static final String GET_ADDRESS_OF_ORDER = "";

	private static final String INSERT_ADDRESS_FOR_USER = ""
			+ "VALUES (?,?);";

	private static final String GET_ADDRESS_BY_ID = "";

	private static final String DELETE_ADDRESS = "";
	
	private static final String IS_ADDRESS_EXCIST = "";

	@Autowired
	private Connection con;

	@Autowired
	private UserDAO dao;

	@Autowired
	private DBConnection db;

	
	@Override
	public HashSet<Address> getAddressOfUser(User u) throws ClassNotFoundException, SQLException {
		// Connection con = DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement(GET_ALL_ADDRESSES_OF_USER);
		ps.setLong(1, u.getId());

		ResultSet rs = ps.executeQuery();
		HashSet<Address> addresses = new HashSet<>();
		while (rs.next()) {
			Address address = new Address();
			address.setAddress(rs.getString("address"));
			address.setUser(u);
			address.setAddress_id(rs.getLong(1));
			addresses.add(address);
		}
		return addresses;
	}

	@Override
	public Address getAddressOfOrder(Order o) throws ClassNotFoundException, SQLException, AddressException {
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(GET_ADDRESS_OF_ORDER);) {
			ps.setLong(1, o.getId());
			Address adr = new Address();

			rs = ps.executeQuery();
			if (!rs.next()) {

				return new Address();
			}
			adr.setAddress(rs.getString("a.address"));
			adr.setUser(o.getUser());
			return adr;

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	@Override
	public void insertAddressForUser(Address address) throws SQLException {
		String query = INSERT_ADDRESS_FOR_USER;
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, address.getAddress());
			ps.setLong(2, address.getUser().getId());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			address.setAddress_id(rs.getInt(1));
			System.out.println("Id of address");
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}


	@Override
	public Address getAddresById(long id) {
		Address address = new Address();
		try {
			PreparedStatement ps = con.prepareStatement(GET_ADDRESS_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			long address_id = rs.getLong(1);
			String address1 = rs.getString(2);
			long user_id = rs.getLong(3);

			address.setAddress(address1);
			address.setUser(dao.getUserByID(user_id));
			address.setAddress_id(address_id);
			return address;

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return address;
	}

	
	@Override
	public void deleteAddress(long userId, long addressId) {
		try {
			PreparedStatement ps = con.prepareStatement(DELETE_ADDRESS);
			ps.setLong(1, userId);
			ps.setLong(2, addressId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	public boolean hasSuchAddress(String address, long userId) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(IS_ADDRESS_EXCIST);) {

			ps.setString(1, address);
			ps.setLong(2, userId);

			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("count") > 0;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

}
