package com.example.demo.product.controllers;


import com.example.demo.product.dao.OrderDAO;
import com.example.demo.product.model.Address;
import com.example.demo.product.model.Order;
import com.example.demo.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
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

//    public String createOrder(@RequestBody Order order, HttpSession session) throws SQLException, ChangeSetPersister.NotFoundException {
//        if (isLogged(session)) {
//            order.setId((long) (session.getAttribute("id")));
//            System.out.println("The order was saved");
//            return this.orderDAO.createOrder(order);
//        } else {
//            return "The order was not saved";
//        }
//    }

    @GetMapping("/orders/{addressId}")
    public Address deliveryOrder(@PathVariable long addressId) {
        return this.orderDAO.deliveryOrder(addressId);
    }

    @GetMapping("/orders/hours")
    public List<String> listAllHoursForDelivery() {
        return this.orderDAO.listAllHoursForDelivery();
    }

}
