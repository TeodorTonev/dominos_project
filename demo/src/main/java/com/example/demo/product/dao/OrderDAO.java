package com.example.demo.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;
import java.sql.Date;

import com.example.demo.product.Exception.AddressException;
import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.Exception.UserException;
import com.example.demo.product.controllers.UserController;
import com.example.demo.product.model.*;
import com.sun.prism.impl.Disposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Component
public class OrderDAO {


	private static final String LIST_ALL_ORDERS_FROM_USER = "select  u.first_name, u.last_name, c.name, l.neighborhood_name, p.name, e.quantity, o.date\n" +
			"from orders_products e \n" +
			"join products p on (e.products_id = p.id)\n" +
			"join order_details o on (e.detail_id = o.id)\n" +
			"join users u on (o.id_user = u.id)\n" +
			"join restaurants r on (o.id_restaurant = r.id)\n" +
			"join locations l on (r.id_locations = l.id)\n" +
			"join cities c on (l.id_city = c.id)\n" +
			"where o.id_user = ?;";
	private static final String INSERT_ORDER_FOR_USER = "insert into dominos.order_details values (null, ?, ?, ?, ?);";
	private static final String INSERT_INTO_ORDERS_PRODUCTS_DETAIL_ID_SELECT_MAX_ID_FROM_ORDER_DETAILS = "insert into orders_products (detail_id) select max(id) from order_details;";
	private static final String SELECT_MAX_ID_MAX_ID_FROM_ORDERS_PRODUCTS = "select @MAX_ID:= max(id) from orders_products;";
	private static final String SELECT_PRICE_FROM_DOMINOS_PRODUCTS_WHERE_ID_MAX_ID = "SELECT price FROM dominos.products WHERE id = @MAX_ID;";
	private static final String DELIVERY_ORDER = "SELECT * FROM dominos.addresses_for_order where id = ?;";
	private static final String REMOVE_PRODUCT_BY_ORDER = "DELETE FROM dominos.orders_products WHERE id = ?;";
	private static final String UPDATE_DOMINOS_ORDERS_PRODUCTS_SET_QUANTITY_PRODUCTS_ID_WHERE_ID_MAX_ID = "UPDATE dominos.orders_products SET quantity = ?, products_id = ? WHERE id = @MAX_ID;";
	private UserController us;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestaurantDAO restaurantDAO;

	public List<ResultOfRequest> listAllOrdersForUser(long id) throws ClassNotFoundException, SQLException, UserException {
		String sql = LIST_ALL_ORDERS_FROM_USER;

		List<ResultOfRequest> res = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<ResultOfRequest>() {
			@Override
			public ResultOfRequest mapRow(ResultSet resultSet, int i) throws SQLException {
				ResultOfRequest result = new ResultOfRequest();
				result.setFirstName(resultSet.getString("u.first_name"));
				result.setLastName(resultSet.getString("u.last_name"));
				result.setCity(resultSet.getString("c.name"));
				result.setNeighborhood(resultSet.getString("l.neighborhood_name"));
				result.setProductName(resultSet.getString("p.name"));
				result.setQuantity(resultSet.getInt("e.quantity"));
				result.setDate(resultSet.getString("o.date"));
				return result;
			}
		});

		return res;
	}

	@Transactional
	public double insertOrderForUser(ResultOfOrder resultOfOrder) throws SQLException {

		int userId = resultOfOrder.getUserId();
		int restaurantId = 3;
		int addressId = resultOfOrder.getAddressId();
		int quantity = resultOfOrder.getQuantity();
		int productId = resultOfOrder.getProductId();
		Date date = Date.valueOf(LocalDate.now());

		long res = jdbcTemplate.update(INSERT_ORDER_FOR_USER,
				userId, restaurantId, addressId, date);

		long temp = jdbcTemplate.update(INSERT_INTO_ORDERS_PRODUCTS_DETAIL_ID_SELECT_MAX_ID_FROM_ORDER_DETAILS);

		long result = jdbcTemplate.queryForObject(SELECT_MAX_ID_MAX_ID_FROM_ORDERS_PRODUCTS, Long.class);
		long upd = jdbcTemplate.update(UPDATE_DOMINOS_ORDERS_PRODUCTS_SET_QUANTITY_PRODUCTS_ID_WHERE_ID_MAX_ID, quantity, productId);

		double priceForMoment = jdbcTemplate.queryForObject(SELECT_PRICE_FROM_DOMINOS_PRODUCTS_WHERE_ID_MAX_ID, Double.class);
		return priceForMoment;

	}

	public Address deliveryOrder(long addressId) {
		String sql = DELIVERY_ORDER;
		Map map = jdbcTemplate.queryForMap(sql, addressId);
		Address address = new Address();

		address.setAddressId(Long.valueOf((Integer)map.get("id")));
		address.setAddress((String)map.get("address"));
		address.setUserId((Long.valueOf((Integer)map.get("user_id"))));

		return address;
	}

	public List<Restaurant> deliveryFromAPlace() throws ProductException, URLException, SQLException {
		return this.restaurantDAO.getAllRestaurants();
	}

	public List<String> listAllHoursForDelivery() {
		LocalTime ldt = LocalTime.now();
		String time = ldt.toString();
		String[] tokens = time.split(":");
		String token1 = tokens[0];
		int tok = Integer.parseInt(token1);
		List<String> result = new ArrayList<>();
		result.add("сега");
		for (int hour = 11; hour < 24; hour++) {
			for (int min = 0; min < 60; min += 10) {
				if (hour > tok) {
					if (min == 0) {
						result.add(hour + ":" + min + "0");
					} else {
						result.add(hour + ":" + min);
					}
				}
			}
		}
		return result;
	}

	public boolean removeProductByOrder(int id) {
		String sql = REMOVE_PRODUCT_BY_ORDER;
		Object[] args = new Object[] {id};

		return jdbcTemplate.update(sql, args) == 1;
	}
}

