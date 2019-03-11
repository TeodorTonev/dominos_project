package com.example.demo.product.controllers;

import com.example.demo.product.dao.AdminDAO;
import com.example.demo.product.model.Admin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RestController
public class AdminController {

    @NotNull
    private AdminDAO adminDAO;
    private static boolean isHeAnAdmin;

    @NotNull
    private Admin admin;

    private boolean isAddmin(HttpSession h) {
        if (h.isNew() || isHeAnAdmin) {
            return false;
        }
        if (h.getAttribute("id") == null || isHeAnAdmin) {
            return false;
        }
        isHeAnAdmin = true;
        return isHeAnAdmin;
    }

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
        if (isLogged(session) && isAddmin(session)) {
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
}
