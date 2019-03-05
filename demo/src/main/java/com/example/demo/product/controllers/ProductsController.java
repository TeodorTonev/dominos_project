package com.example.demo.product.controllers;

import com.example.demo.product.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.product.model.Product;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductDAO productDao;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @PostMapping("/product")
    public long addProduct(@RequestBody Product product) {
        return productDao.addProduct(product);
    }

//    @GetMapping("/products/{productId}")
//    public Product getProductDetails(@PathVariable long productId) {
//        return productDao.getProductById(productId);
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/products")
//    public long addProduct(@RequestBody Product product) {
//        return productDao.addProduct(product);
//    }
}
