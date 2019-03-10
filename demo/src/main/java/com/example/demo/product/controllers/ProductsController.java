package com.example.demo.product.controllers;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.dao.ProductDAO;
import com.example.demo.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.crypto.Des;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class ProductsController {

    @Autowired
    private ProductDAO productDao;

    @GetMapping("/products")
    public List<String> getAllProducts() {
        return productDao.getAllProducts();
    }


    @GetMapping("/products/{productId}")
    public Product getProductDetails(@PathVariable long productId) throws ProductException, URLException, SQLException {
        return productDao.getProductById(productId);
    }

    @GetMapping ("/products/drinks")
    public List<Drink> getAllDrinks() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllDrinks();
    }

    @GetMapping ("/products/sauces")
    public List<Sauce> getAllSauces() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllSauces();
    }

    @GetMapping ("/products/customizes")
    public List<String> getAllCustomizes() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllCustomize();
    }

    @GetMapping ("/products/desserts")
    public List<Dessert> getAllDesserts() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllDesserts();
    }

    @GetMapping ("/products/pizzas")
    public List<Pizza> getAllPizzas() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllPizzas();
    }

    @GetMapping ("/products/sandwiches")
    public List<Sandwich> getAllSandwiches() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllSandwiches();
    }

    @GetMapping ("/products/salads")
    public List<Salads> getAllSalads() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllSalads();
    }

    @GetMapping ("/products/starters")
    public List<Starters> getAllStarters() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllStarters();
    }

    @GetMapping ("/products/chikens")
    public List<Chicken> getAllChikens() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllChiken();
    }

    @GetMapping ("/products/pastas")
    public List<Pasta> getAllPastas() throws ProductException, URLException, SQLException, ChangeSetPersister.NotFoundException {
        return productDao.getAllPastas();
    }
}
