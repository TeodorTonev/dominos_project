package com.example.demo.product.controllers;

import com.example.demo.product.DTO.LoginDTO;
import com.example.demo.product.dao.UserDAO;
import com.example.demo.product.model.Address;
import com.example.demo.product.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping("/users/login")
    public User login(LoginDTO user) {
        return this.userDAO.login(user);
    }

    @PostMapping("/addaddresses")
    public void addAddress(@RequestBody Address address) {
        try {
            this.userDAO.insertAddressForUser(address);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Incorrect connection!");
        }
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long id) {
        User user = null;
        try {
            user = this.userDAO.getUserByID(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Incorrect connection!");
        }
        return user;
    }

    @PostMapping("/users/register")
    public void registerUser(@RequestBody User user) {
        try {
            this.userDAO.register(user);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Invalid user");
        }
    }

    @PostMapping("/users/hasSuch")
    public boolean hasSuchEmail(@RequestBody String email) throws SQLException, ClassNotFoundException {
        return this.userDAO.hasSuchEmail(email);
    }

    @PostMapping("/user/getUser")
    public User getUser(@RequestBody String email) {
        try {
            this.userDAO.getUser(email);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Invalid email address");
        }
        return null;
    }

    @PostMapping("users/updateUser")
    public boolean updateUser(@RequestBody User user) throws SQLException {
        return this.userDAO.updateUser(user);
    }

    @PostMapping("/users/{userId}")
    public User getUserById(@PathVariable long id) throws SQLException, ClassNotFoundException {
        return this.userDAO.getUserByID(id);
    }

    @PostMapping("/users/exsistUser")
    public boolean existsUser(@RequestBody String email, String password) throws SQLException {
        return this.userDAO.existsUser(email, password);
    }
}
