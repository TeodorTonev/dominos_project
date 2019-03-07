package com.example.demo.product.controllers;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.dao.RestaurantDAO;
import com.example.demo.product.model.Drink;
import com.example.demo.product.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;


@RestController
public class RestaurantController {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() throws ProductException, URLException, SQLException {
        return restaurantDAO.getAllRestaurants();
    }

}
