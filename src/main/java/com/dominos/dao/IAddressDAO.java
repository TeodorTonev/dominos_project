package com.dominos.dao;

import java.sql.SQLException;
import java.util.HashSet;

import com.dominos.exceptions.AddressException;
import com.dominos.models.Order;
import com.dominos.models.User;
import com.dominos.models.Address;

public interface IAddressDAO {

	HashSet<Address> getAddressOfUser(User u) throws ClassNotFoundException, SQLException;

	Address getAddressOfOrder(Order o) throws ClassNotFoundException, SQLException, AddressException;

	void insertAddressForUser(Address address) throws SQLException;

	Address getAddresById(long id);

	void deleteAddress(long userId, long addressId);
	boolean hasSuchAddress(String address,long userId) throws SQLException, ClassNotFoundException;


}
