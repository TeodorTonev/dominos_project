package com.example.demo.product.dao;

import java.sql.SQLException;
import java.util.HashSet;

import com.example.demo.product.Exception.AddressException;
import com.example.demo.product.model.Address;
import com.example.demo.product.model.Order;
import com.example.demo.product.model.User;

public interface IAddressDAO {

	HashSet<Address> getAddressOfUser(User u) throws ClassNotFoundException, SQLException;

	Address getAddressOfOrder(Order o) throws ClassNotFoundException, SQLException, AddressException;

	void insertAddressForUser(Address address) throws SQLException;

	Address getAddresById(long id);

	void deleteAddress(long userId, long addressId);
	boolean hasSuchAddress(String address, long userId) throws SQLException, ClassNotFoundException;


}
