package com.example.demo.product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdminDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Boolean removeOldOrders() {
        String sql = "DELETE FROM dominos.order_details WHERE date < '2015-01-01 00:00:00';";

        return jdbcTemplate.update(sql) == 1;
    }
}
