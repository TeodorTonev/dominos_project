package com.example.demo.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Component
public class OrderDAO {

	@NotNull
	private Order order;

	private UserController us;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestaurantDAO restaurantDAO;


	private static final String PRODUCT_ID_AND_QUANTITY = "select @MAX_ID:= max(id) from orders_products;\n" +
															"UPDATE dominos.orders_products\n" +
															"SET quantity = ?, products_id = ?\n" +
															"WHERE id = @MAX_ID;";

	private static final String INSERT_INTO_ORDER_PRODUCTS_FROM_ORDER_DETAILS = "INSERT INTO orders_products (detail_id) select max(id) from order_details;";

	private static final String INSERT_INTO_ORDER_DETAILS = "insert into dominos.order_details values (null, ?, ?, ?, ?);";

	private static final String GET_ALL_ORDERS_FOR_USER = "select  u.first_name, u.last_name, c.name, l.neighborhood_name, p.name, e.quantity, o.date\n" +
															"from orders_products e \n" +
															"join products p on (e.products_id = p.id)\n" +
															"join order_details o on (e.detail_id = o.id)\n" +
															"join users u on (o.id_user = u.id)\n" +
															"join restaurants r on (o.id_restaurant = r.id)\n" +
															"join locations l on (r.id_locations = l.id)\n" +
															"join cities c on (l.id_city = c.id)\n" +
															"where o.id_user = ?;";

	private static final String DELIVERY_ORDER = "SELECT * FROM dominos.addresses_for_order where id = ?;";


	public List<ResultOfRequest> listAllOrdersForUser(long id) throws ClassNotFoundException, SQLException, UserException {
		String sql = GET_ALL_ORDERS_FOR_USER;

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


	public long insertProductsFromOrder(long productId, int quantity, long addressId, HttpSession session) throws SQLException {
		Product product = new Product();
		product.setId(productId);
		product.setQuantity(quantity);
		Date date = Date.valueOf(LocalDate.now());

		long model = jdbcTemplate.update(INSERT_INTO_ORDER_DETAILS,
				session.getId(), null, addressId, date);

		long temp = jdbcTemplate.update(INSERT_INTO_ORDER_PRODUCTS_FROM_ORDER_DETAILS);

		long result = jdbcTemplate.update(PRODUCT_ID_AND_QUANTITY,
				product.getQuantity(), product.getId());

		return 1;
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
		List<String> result = new ArrayList<>();
		result.add("сега");
		for (int hour = 10; hour < 24; hour++) {
			for (int min = 0; min < 60; min += 10) {
				if (min == 0) {
					result.add(hour + ":" + min + "0");
				} else {
					result.add(hour + ":" + min);
				}
			}
		}
		return result;
	}

//	public String removeProductByOrder(Long productId) {
//
//	}

}

