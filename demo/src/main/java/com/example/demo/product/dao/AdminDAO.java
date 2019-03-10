package com.example.demo.product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdminDAO {

	private static final String REMOVE_OLD_ORDERS = "DELETE FROM dominos.order_details WHERE date < ?;"
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
	private LocalDate localDate = LocalDate.now().minusYears(YEARS_TO_REMOV_ORDERS);

    public Boolean removeOldOrders() {
        String sql = REMOVE_OLD_ORDERS;
		Object[] args - new Object[] (this.localDate);

        return jdbcTemplate.update(sql, args) == 1;
    }
}
