package com.dominos.dao;

import java.sql.SQLException;

import com.dominos.dto.LoginDTO;
import com.dominos.models.User;

public interface IUserDAO {

	public  void register(User u) throws SQLException, ClassNotFoundException;
	public  boolean existsUser(String e_mail,String pass) throws SQLException, ClassNotFoundException ;
	public  User getUser(String e_mail) throws SQLException, ClassNotFoundException ;

	public boolean updateUser(User user) throws SQLException ;

	public User getUserByID(long id)throws SQLException, ClassNotFoundException ;
	
	public User login(LoginDTO user); 

}
