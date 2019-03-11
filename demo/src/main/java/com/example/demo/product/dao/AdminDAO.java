package com.example.demo.product.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.product.ENUM.*;
import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.model.*;
import com.example.demo.product.DTO.LoginDTO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

@Component
public class AdminDAO {

	private static final String REMOVE_OLD_ORDERS = "DELETE FROM dominos.order_details WHERE date < ?;"
	private static final int YEARS_TO_REMOV_ORDERS = 10;
	
	private static final String INSERT_PIZZA_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_DEAL_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_STARTER_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_CHICKEN_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_PASTA_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_SALAD_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_SANDWICH_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_DIP_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_DESERT_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_DRINK_SQL = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	
	private static final String REMOVE_PRODUCT_SQL = "DELETE FROM dominos.products WHERE id = ?";
	
	private static final String GET_USER_BY_ID = "SELECT * FROM dominos.users WHERE id=?;";
	private static final String REMOVE_USER = "DELETE FROM dominos.users WHERE id = ?";
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
	private LocalDate localDate = LocalDate.now().minusYears(YEARS_TO_REMOV_ORDERS);

    public Boolean removeOldOrders() {
        String sql = REMOVE_OLD_ORDERS;
		Object[] args - new Object[] (this.localDate);

        return jdbcTemplate.update(sql, args) == 1;
    }
	
	
	public int addProductPizza(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 2;//product type

		return jdbcTemplate.update(INSERT_PIZZA_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductDeals(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 1;//product type

		return jdbcTemplate.update(INSERT_DEAL_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductStarter(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 3;//product type

		return jdbcTemplate.update(INSERT_STARTER_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductChicken(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 4;//product type

		return jdbcTemplate.update(INSERT_CHICKEN_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductPasta(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 5;//product type

		return jdbcTemplate.update(INSERT_PASTA_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductSalad(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 6;//product type

		return jdbcTemplate.update(INSERT_SALAD_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductSandwich(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 7;//product type

		return jdbcTemplate.update(INSERT_SANDWICH_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductDips(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 8;//product type

		return jdbcTemplate.update(INSERT_DIP_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductDesserts(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 9;//product type

		return jdbcTemplate.update(INSERT_DESERT_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}

	public int addProductDrinks(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 10;//product type

		return jdbcTemplate.update(INSERT_DRINK_SQL,
				id, name, description, price, size, pictureURL, type_id);
	}


	public boolean removeProduct(int id) {
		String sql = REMOVE_PRODUCT_SQL;
		Object[] args = new Object[] {id};

		return jdbcTemplate.update(sql, args) == 1;
	}
	
	
	public User getUserByID(long id) throws SQLException, ClassNotFoundException {
		String sql = GET_USER_BY_ID;
		Map map = jdbcTemplate.queryForMap(sql, id);
		User user = new User();

		user.setId(Long.valueOf((Integer)map.get("id")));
		user.setFirstName((String)map.get("first_name"));
		user.setLastName((String)map.get("last_name"));
		user.setAddress((String)map.get("address"));
		user.setEmail((String)map.get("email"));
		user.setPassword((String)map.get("password"));

		return user;
	}

	public boolean removeUser(int id) {
		String sql = REMOVE_USER;
		Object[] args = new Object[] {id};

		return jdbcTemplate.update(sql, args) == 1;
	}
	
	
}
