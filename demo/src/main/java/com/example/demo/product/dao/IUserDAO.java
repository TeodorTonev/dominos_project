package com.example.demo.product.dao;

import java.sql.SQLException;
import com.example.demo.product.DTO.LoginDTO;
import com.example.demo.product.model.User;

public interface IUserDAO {

//	public  void register(User u) throws SQLException, ClassNotFoundException;
//
// public  boolean existsUser(String eMail, String pass) throws SQLException, ClassNotFoundException ;
//
// public User getUserId(String e_mail) throws SQLException, ClassNotFoundException ;
//
	public User getUserByID(long id)throws SQLException, ClassNotFoundException ;
//
//	User login(LoginDTO user);
}
