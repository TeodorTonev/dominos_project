package com.example.demo.product.dao;

import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class RestaurantDAO {

	private static final String GET_ALL_RESTAURANTS = "SELECT m.name, e.neighborhood_name\n" +
													  "FROM locations e\n" +
													  "JOIN cities m ON (e.id_city = m.id);"

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Restaurant> getAllRestaurants() throws URLException, ProductException, SQLException {
        String sql = GET_ALL_RESTAURANTS;

        List<Restaurant> restaurants = jdbcTemplate.query(sql, new RowMapper<Restaurant>() {
            @Override
            public Restaurant mapRow(ResultSet resultSet, int i) throws SQLException {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(resultSet.getString("name"));
                restaurant.setLoc(resultSet.getString("neighborhood_name"));
                return restaurant;
            }
        });
        return restaurants;
    }
}
