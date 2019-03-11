package com.example.demo.product.controllers;


import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.Exception.UserException;
import com.example.demo.product.dao.OrderDAO;
import com.example.demo.product.model.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    private static boolean isLogged(HttpSession s){
        if(s.isNew()){
            return false;
        }
        if(s.getAttribute("id") == null){
            return false;
        }
        return true;
    }

    @GetMapping("/orders/{addressId}")
    public Address deliveryOrder(@PathVariable long addressId) {

        return this.orderDAO.deliveryOrder(addressId);
    }

    @GetMapping("/orders/hours")
    public List<String> listAllHoursForDelivery() {
        return this.orderDAO.listAllHoursForDelivery();
    }

    @GetMapping("/orders/restaurants")
    public List<Restaurant> listAllRestaurants() throws ProductException, URLException, SQLException {
        return this.orderDAO.deliveryFromAPlace();
    }

    @PostMapping("/yourOrders/{id}")
    public List<ResultOfRequest>  listAllOrdersOnUser(@PathVariable long id, HttpSession session) throws SQLException, ClassNotFoundException, UserException {
        if (isLogged(session)) {
            return this.orderDAO.listAllOrdersForUser(id);
        }
        else {
            System.out.println("The user is not logged");
            return new ArrayList<>();
        }
    }

    @PostMapping("/product/insertOrder")
    public double insertOrderFromUser(@RequestBody ResultOfOrder resultOfOrder, HttpSession session) throws SQLException, ChangeSetPersister.NotFoundException {
        if(isLogged(session)) {
            System.out.println("The element was saved");
            return this.orderDAO.insertOrderForUser(resultOfOrder);
        }
        else{
            System.out.println("The element was not saved");
            return -1;
        }
    }

    @PostMapping("/products/deleteByOrder/{id}")
    public boolean removeProductByOrder(@PathVariable int id, HttpSession session) {
        if (isLogged(session)) {
            return this.orderDAO.removeProductByOrder(id);
        }
        else {
            return false;
        }
    }
}
