package com.example.demo.product.controllers;

import com.example.demo.product.DTO.LoginDTO;
import com.example.demo.product.Exception.UserException;
import com.example.demo.product.dao.UserDAO;
import com.example.demo.product.model.Address;
import com.example.demo.product.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDAO userDAO;

    private static boolean isLogged(HttpSession s){
        if(s.isNew()){
            return false;
        }
        if(s.getAttribute("id") == null){
            return false;
        }
        return true;
    }

    @PostMapping("/users/login")
    public String login(@RequestBody LoginDTO user, HttpServletRequest request) throws SQLException {
        User u = this.userDAO.login(user);
        HttpSession session = request.getSession();
        session.setAttribute("id", u.getId());

        return "OK";
    }

    @PostMapping("/users/signout")
    public String logout(HttpServletRequest request) throws UserException {
        HttpSession session = request.getSession();
        session.invalidate();

        return "Signout";
    }

    @PostMapping("/addaddresses")
    public int addAddress(@RequestBody Address address, HttpSession session) throws SQLException {
        if(isLogged(session)) {
            address.setUserId((long) (session.getAttribute("id")));
            return this.userDAO.insertAddressForUser(address);
        }
        else{
            return -1;
        }
    }

    @PostMapping("/users/delete/{id}")
    public boolean removeUser(@PathVariable int id) {
        return this.userDAO.removeUser(id);
    }


    @PostMapping("/users/register")
    public int registerUser(@RequestBody User user) throws SQLException, ClassNotFoundException {
            return this.userDAO.register(user);
    }

//
//    @PostMapping("/user/getUserId")
//    public User getUserEmail(@RequestBody String email) {
//        try {
//            this.userDAO.getUserId(email);
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Invalid email address");
//        }
//        return null;
//    }

//
     @GetMapping("/users/{userId}")
     public User getUserById(@PathVariable long id) throws SQLException, ClassNotFoundException {
            return this.userDAO.getUserByID(id);
     }
}
