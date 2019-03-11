package com.example.demo.product.model;

import com.example.demo.product.dao.AdminDAO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Admin extends User implements Runnable{
    public static final int TIME_FOR_WORK_WITH_DATA = 50000;

    @NotNull
    private AdminDAO adminDAO;
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(TIME_FOR_WORK_WITH_DATA);
                this.adminDAO.removeOldOrders();
            } catch (InterruptedException e) {
                System.out.println("Time is for restart");
                e.printStackTrace();
            }
        }
    }
}
