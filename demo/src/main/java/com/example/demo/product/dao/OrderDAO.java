package com.example.demo.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

import com.example.demo.product.Exception.AddressException;
import com.example.demo.product.model.Address;
import com.example.demo.product.model.Order;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//	@Autowired
//	IUserDAO userDao;


	private static final String GET_ORDER_BY_ID = "";
	private static final String INSERT_PRODUCT_IN_ORDER = "";
	private static final String INSERT_ORDER_FOR_USER = "";

	private static final String GET_ALL_USER_ORDERS = "";

	private static final String GET_PRODUCTS_OF_ORDER_BY_ID = "";
	private static final String GET_ACTIVE_ORDER_FOR_USER = "";

	private static final String GET_ALL_ORDERS_ON_ADDRESS = "";

	private static final String SET_ADDRESS_OF_ORDERS_NULL = "";


	public TreeSet<Order> getOrdersForUser(long user_id) throws ClassNotFoundException, SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(GET_ALL_USER_ORDERS);) {
			ps.setLong(1, user_id);
			rs = ps.executeQuery();

			TreeSet<Order> orders = new TreeSet<>(new Comparator<Order>() {
				@Override
				public int compare(Order o1, Order o2) {
					return o2.getDatetime().compareTo(o1.getDatetime());
				}
			});
			Order order = null;
			while (rs.next()) {

				HashMap<Product, Integer> products = this.getProductsForOrder(rs.getLong("id"));
				// User user = userDao.getUserByID(user_id);
				LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
				Address a = new Address();
				a.setAddress(rs.getString("a.address"));
				order = new Order();
				order.setId(rs.getLong("id"));

				order.setDateTime(date);
				order.setProducts(products);
				order.setPrice(rs.getFloat("price"));
				order.setAddres(a);
				order.setDelivered(rs.getBoolean("isDelivered"));

				orders.add(order);
			}
			return orders;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
//


	public HashMap<Product, Integer> getProductsForOrder(long orderId) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_OF_ORDER_BY_ID)) {
			ps.setLong(1, orderId);
			rs = ps.executeQuery();
			HashMap<Product, Integer> productsForOrder = new HashMap<Product, Integer>();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getLong("product_id"));
				product.setName(rs.getString("p.name"));
				product.setPrice(rs.getFloat("price"));
				product.setPictureUrl(rs.getString("p.picture"));
				productsForOrder.put(product, rs.getInt("op.quantity"));
			}
			return productsForOrder;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}


	public void insertOrderForUser(Order order) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		// Connection con = DBconnection.getConnection();
		String query = INSERT_ORDER_FOR_USER;
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			ps.setLong(1, order.getUser().getId());
			ps.setString(2, (order.getDatetime()).toString());
			ps.setFloat(3, order.getPrice());
			ps.setLong(4, order.getAddres().getId());
			ps.setBoolean(5, order.isDelivered());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			order.setId(rs.getInt(1));

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	public void insertProductsFromOrder(long orderId, HashMap<Product, Integer> cart) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();

		for (Entry<Product, Integer> entry : cart.entrySet()) {
			Product product = entry.getKey();
			int quantity = (int) entry.getValue();

			try (PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT_IN_ORDER);) {
				ps.setLong(1, product.getId());
				ps.setLong(2, orderId);
				ps.setInt(3, quantity);
				ps.executeUpdate();
			} finally {

			}
		}
	}

	public Order getActiveOrderForUser(User user) {

		for (Order order : user.getOrders()) {
			if (!order.isDelivered()) {
				return order;
			}
		}
		Order order = new Order();
		order.setUser(user);
		return order;

	}

	public void deleteOrderOnAddress(long address_id) throws SQLException {
		Connection con = jdbcTemplate.getDataSource().getConnection();
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(GET_ALL_ORDERS_ON_ADDRESS);) {
			ps.setLong(1, address_id);
			rs = ps.executeQuery();
			while (rs.next()) {

				long order_id=rs.getLong(1);
				System.out.println("ord id "+ order_id);

				PreparedStatement ps1=con.prepareStatement(SET_ADDRESS_OF_ORDERS_NULL);
				ps1.setLong(1,order_id);
				int res=ps1.executeUpdate();
				System.out.println("result "+ res);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
//
//	@Override
//	public Order getOrderById(long id) throws SQLException, ClassNotFoundException, AddressException {
//		Connection con = jdbcTemplate.getDataSource().getConnection();
//		ResultSet rs = null;
//		try (PreparedStatement ps = con.prepareStatement(GET_ORDER_BY_ID)) {
//			ps.setLong(1, id);
//			rs = ps.executeQuery();
//
//			if (rs.next()) {
//				Order order = new Order();
//				order.setId(rs.getLong(1));
//				order.setDateTime((rs.getTimestamp((2)).toLocalDateTime()));
//				order.setUser(userDao.getUserByID(rs.getLong(3)));
//				order.setAddres(.getAddresById(rs.getLong(4)));
//				order.setPrice(rs.getFloat(5));
//				order.setDelivered(true);
//				return order;
//			}
//		}
//		throw new AddressException();
//	}

//	public String createOrder(Order order) throws SQLException {
//
//
//	}

	public Address deliveryOrder(long addressId) {
		String sql = "SELECT * FROM dominos.addresses_for_order where id = ?;";
		Map map = jdbcTemplate.queryForMap(sql, addressId);
		Address address = new Address();

		address.setAddressId(Long.valueOf((Integer)map.get("id")));
		address.setAddress((String)map.get("address"));
		address.setUserId((Long.valueOf((Integer)map.get("user_id"))));

		return address;
	}

	public void deliveryFromAPlace() {

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

	public String deliveryTime() {
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
		return result.get(new Random().nextInt(result.size()));
	}

//	public String removeProductByOrder(Long productId) {
//
//	}

}

