package com.example.demo.product.controllers;

import com.example.demo.product.dao.AdminDAO;
import com.example.demo.product.model.Admin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@RestController
public class AdminController {

    @NotNull
    private AdminDAO adminDAO;

    @NotNull
    private Admin admin;
	
	@Autowired
    private ProductDAO productDao;

    private static boolean isLogged(HttpSession s){
        if(s.isNew()){
            return false;
        }
        if(s.getAttribute("id") == null){
            return false;
        }
        return true;
    }

    @PostMapping("/orders/deleteOld")
    public Boolean removeOldOrder(HttpSession session) {
        if (isLogged(session)) {
            Thread thread = new Thread(this.admin);
            thread.setDaemon(true);
            thread.start();
            return true;
        }
        else {
            System.out.println("The administration is not logged");
            return false;
        }
    }
	
	
	@PostMapping("/product/addPizza")
    public long addProductPizza(@RequestBody Product product) {
        return productDao.addProductPizza(product);
    }

    @PostMapping("/product/addDeals")
    public long addProductDeals(@RequestBody Product product) {
        return productDao.addProductDeals(product);
    }

    @PostMapping("/product/addStarter")
    public long addProductStarter(@RequestBody Product product) {
        return productDao.addProductStarter(product);
    }

    @PostMapping("/product/addChiken")
    public long addProductChicken(@RequestBody Product product) {
        return productDao.addProductChicken(product);
    }

    @PostMapping("/product/addPasta")
    public long addProductPasta(@RequestBody Product product) {
        return productDao.addProductPasta(product);
    }

    @PostMapping("/product/addSandwich")
    public long addProductSSandwich(@RequestBody Product product) {
        return productDao.addProductSandwich(product);
    }

    @PostMapping("/product/addDips")
    public long addProductDips(@RequestBody Product product) {
        return productDao.addProductDips(product);
    }

    @PostMapping("/product/addDesserts")
    public long addProductDesserts(@RequestBody Product product) {
        return productDao.addProductDesserts(product);
    }

    @PostMapping("/product/addSalad")
    public long addProductSalad(@RequestBody Product product) {
        return productDao.addProductSalad(product);
    }

    @PostMapping("/product/addDrink")
    public long addProductDrink(@RequestBody Product product) {
        return productDao.addProductDrinks(product);
    }
	
	
	@PostMapping("/products/{id}")
    public boolean removeProduct(@PathVariable int id) {
        return this.productDao.removeProduct(id);
    }
	
	
	
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable long id) throws SQLException, ClassNotFoundException {
        return this.userDAO.getUserByID(id);
    }
	 
	@PostMapping("/users/delete/{id}")
    public boolean removeUser(@PathVariable int id) {
        return this.userDAO.removeUser(id);
    }
}
