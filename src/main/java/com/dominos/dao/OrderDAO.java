package com.dominos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dominos.db.DBConnection;
import com.dominos.models.Address;
import com.dominos.exceptions.AddressException;
import com.dominos.models.Product;
import com.dominos.models.User;
import com.dominos.models.Order;

@Component
public class OrderDAO implements IOrderDAO {

	@Autowired
	DBConnection DBconnection;

	@Autowired
	IUserDAO userDao;
	
	@Autowired
	IAddressDAO addressDao;

	@Autowired
	Connection con;

	private static final String GET_ORDER_BY_ID = "";
	private static final String INSERT_PRODUCT_IN_ORDER = "";
	private static final String INSERT_ORDER_FOR_USER = "";

	private static final String GET_ALL_USER_ORDERS = "";

	private static final String GET_PRODUCTS_OF_ORDER_BY_ID = "";
	private static final String GET_ACTIVE_ORDER_FOR_USER = "";

	private static final String GET_ALL_ORDERS_ON_ADDRESS = "";
	
	private static final String SET_ADDRESS_OF_ORDERS_NULL = "";

	@Override
	public TreeSet<Order> getOrdersForUser(long user_id) throws ClassNotFoundException, SQLException {
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

	
	@Override
	public HashMap<Product, Integer> getProductsForOrder(long orderId) throws SQLException {
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_OF_ORDER_BY_ID);) {
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

	@Override
	public void insertOrderForUser(Order order) throws SQLException {
		// Connection con = DBconnection.getConnection();
		String query = INSERT_ORDER_FOR_USER;
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			ps.setLong(1, order.getUser().getId());
			ps.setString(2, (order.getDatetime()).toString());
			ps.setFloat(3, order.getPrice());
			ps.setLong(4, order.getAddres().getAddress_id());
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
	
	@Override
	public void insertProductsFromOrder(long orderId, HashMap<Product, Integer> cart) throws SQLException {

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

	@Override
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

	public void deleteOrderOnAddress(long address_id) {
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

	@Override
	public Order getOrderById(long id) throws SQLException, ClassNotFoundException, AddressException {
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(GET_ORDER_BY_ID)) {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Order order = new Order();
				order.setId(rs.getLong(1));
				order.setDateTime((rs.getTimestamp((2)).toLocalDateTime()));
				order.setUser(userDao.getUserByID(rs.getLong(3)));
				order.setAddres(addressDao.getAddresById(rs.getLong(4)));
				order.setPrice(rs.getFloat(5));
				order.setDelivered(true);
				return order;
			}
		}   
		throw new AddressException();
	}

}
