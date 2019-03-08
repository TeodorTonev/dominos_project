package com.example.demo.product.dao;

import java.sql.*;
import java.util.*;

import com.example.demo.product.ENUM.*;
import com.example.demo.product.Exception.ProductException;
import com.example.demo.product.Exception.URLException;
import com.example.demo.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class ProductDAO implements IProductDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductDAO() throws URLException {
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

		private static final String GET_PRODUCT_BY_ID_SQL = "SELECT * FROM dominos.product where id = ?;";

	private static final String GET_ALL_DRINKS_SQL = "";
	private static final String GET_ALL_SAUCES_SQL = "";
	private static final String GET_ALL_PIZZAS_SQL = "";
	private static final String GET_ALL_SALADS_SQL = "";
	private static final String GET_ALL_SANDWICHES_SQL = "";
	private static final String GET_ALL_DESERTS_SQL = "";
	private static final String GET_ALL_PASTAS_SQL = "";

	private static final String INSERT_NEW_PRODUCT_SQL = "";
	private static final String INSERT_NEW_CUSTOM_PIZZA_SQL = "";
	private static final String INSERT_CHEESE_NEW_CUSTOM_PIZZA_SQL = "";
	private static final String INSERT_MEAT_NEW_CUSTOM_PIZZA_SQL = "";
	private static final String INSERT_SPICE_NEW_CUSTOM_PIZZA_SQL = "";
	private static final String INSERT_VEGETABLE_NEW_CUSTOM_PIZZA_SQL = "";
	
	/*
	 * ..............
	 */
	
	
	//private static ProductDAO instance = null;
//	private Set<Product> products;
//
//	@Autowired(required = true)
//	private Connection connection;
//
//	public ProductDAO() throws URLException {
//	}

	public List<String> getAllProducts() {
		String sql = "SELECT name FROM dominos.products;";

		List<String> names = jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet resultSet, int i) throws SQLException {
				return resultSet.getString(1);
			}
		});
		return names;
	}

	public int addProduct(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 2;//product.getTypeProduct();

		return jdbcTemplate.update("insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);",
				id, name, description, price, size, pictureURL, type_id);

	}

	public boolean removeProduct(int id) {
		String sql = "DELETE FROM dominos.products WHERE id = ?";
		Object[] args = new Object[] {id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	@Override
	public Product getProductById(long id) throws ProductException, URLException, SQLException {
		String sql = "SELECT * FROM dominos.products WHERE id=?;";
		Map map = jdbcTemplate.queryForMap(sql, id);
		Product product = new Product();

		product.setId(Long.valueOf((Integer)map.get("id")));
		product.setName((String)map.get("name"));
		product.setDescription((String)map.get("description"));
		product.setPrice((Double)map.get("price"));
		product.setPictureUrl((String)map.get("picture"));

		return product;
	}


	@Override
	public List<Drink> getAllDrinks() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 10;";

		List<Drink> drinks = jdbcTemplate.query(sql, new RowMapper<Drink>() {
			@Override
			public Drink mapRow(ResultSet resultSet, int i) throws SQLException {
				Drink drink = new Drink();
				drink.setId(resultSet.getInt("id"));
				drink.setName(resultSet.getString("name"));
				drink.setSizeP(resultSet.getString("size"));
				drink.setPrice(resultSet.getDouble("price"));
				drink.setPictureUrl(resultSet.getString("picture"));
				return drink;
			}
		});
		return drinks;
	}

	@Override
	public List<Sauce> getAllSauces() throws URLException, ProductException, SQLException {

		String sql = "SELECT * FROM dominos.products where type_category_id = 8;";

		List<Sauce> sauses = jdbcTemplate.query(sql, new RowMapper<Sauce>() {
			@Override
			public Sauce mapRow(ResultSet resultSet, int i) throws SQLException {
				Sauce sauce = new Sauce();
				sauce.setId(resultSet.getLong("id"));
				sauce.setName(resultSet.getString("name"));
				sauce.setDescription(resultSet.getString("description"));
				sauce.setSizeP(resultSet.getString("size"));
				sauce.setPrice(resultSet.getDouble("price"));
				sauce.setPictureUrl(resultSet.getString("picture"));
				return sauce;
			}
		});
		return sauses;
	}

	@Override
	public List<Pizza> getAllPizzas() throws URLException, ProductException, SQLException {

		String sql = "SELECT * FROM dominos.products where type_category_id = 2;";

		List<Pizza> pizzas = jdbcTemplate.query(sql, new RowMapper<Pizza>() {
			@Override
			public Pizza mapRow(ResultSet resultSet, int i) throws SQLException {
				Pizza pizza = new Pizza();
				pizza.setId(resultSet.getLong("id"));
				pizza.setName(resultSet.getString("name"));
				pizza.setDescription(resultSet.getString("description"));
				pizza.setSizeP(resultSet.getString("size"));
				pizza.setPrice(resultSet.getDouble("price"));
				pizza.setPictureUrl(resultSet.getString("picture"));
				return pizza;
			}
		});
		return pizzas;
	}



	public List<String> getAllCustomize() {
		String sql = "SELECT id, name FROM dominos.customize;";

		List<String> customize = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet resultSet, int i) throws SQLException {
				String custom = (resultSet.getInt("id")) + " " + (resultSet.getString("name"));
				return custom;
			}
		});
		return customize;
	}

	public List<Dessert> getAllDesserts() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 9;";

		List<Dessert> desserts = jdbcTemplate.query(sql, new RowMapper<Dessert>() {
			@Override
			public Dessert mapRow(ResultSet resultSet, int i) throws SQLException {
				Dessert dessert = new Dessert();
				dessert.setId(resultSet.getLong("id"));
				dessert.setDescription(resultSet.getString("description"));
				dessert.setName(resultSet.getString("name"));
				dessert.setPrice(resultSet.getDouble("price"));
				dessert.setPictureUrl(resultSet.getString("picture"));
				return dessert;
			}
		});
		return desserts;
	}

	public List<Sandwich> getAllSandwiches() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 7;";

		List<Sandwich> desserts = jdbcTemplate.query(sql, new RowMapper<Sandwich>() {
			@Override
			public Sandwich mapRow(ResultSet resultSet, int i) throws SQLException {
				Sandwich sandwich = new Sandwich();
				sandwich.setId(resultSet.getLong("id"));
				sandwich.setDescription(resultSet.getString("description"));
				sandwich.setName(resultSet.getString("name"));
				sandwich.setPrice(resultSet.getDouble("price"));
				sandwich.setPictureUrl(resultSet.getString("picture"));
				return sandwich;
			}
		});
		return desserts;
	}

	public List<Salads> getAllSalads() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 6;";

		List<Salads> salads = jdbcTemplate.query(sql, new RowMapper<Salads>() {
			@Override
			public Salads mapRow(ResultSet resultSet, int i) throws SQLException {
				Salads salad = new Salads();
				salad.setId(resultSet.getLong("id"));
				salad.setDescription(resultSet.getString("description"));
				salad.setName(resultSet.getString("name"));
				salad.setPrice(resultSet.getDouble("price"));
				salad.setPictureUrl(resultSet.getString("picture"));
				return salad;
			}
		});
		return salads;
	}

	public List<Starters> getAllStarters() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 3;";

		List<Starters> starters = jdbcTemplate.query(sql, new RowMapper<Starters>() {
			@Override
			public Starters mapRow(ResultSet resultSet, int i) throws SQLException {
				Starters starter = new Starters();
				starter.setId(resultSet.getLong("id"));
				starter.setDescription(resultSet.getString("description"));
				starter.setName(resultSet.getString("name"));
				starter.setPrice(resultSet.getDouble("price"));
				starter.setPictureUrl(resultSet.getString("picture"));
				return starter;
			}
		});
		return starters;
	}

	public List<Chicken> getAllChiken() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 4;";

		List<Chicken> chickens = jdbcTemplate.query(sql, new RowMapper<Chicken>() {
			@Override
			public Chicken mapRow(ResultSet resultSet, int i) throws SQLException {
				Chicken chicken = new Chicken();
				chicken.setId(resultSet.getLong("id"));
				chicken.setDescription(resultSet.getString("description"));
				chicken.setName(resultSet.getString("name"));
				chicken.setPrice(resultSet.getDouble("price"));
				chicken.setPictureUrl(resultSet.getString("picture"));
				return chicken;
			}
		});
		return chickens;
	}

	public List<Pasta> getAllPastas() throws URLException, ProductException, SQLException {
		String sql = "SELECT * FROM dominos.products where type_category_id = 5;";

		List<Pasta> pastas = jdbcTemplate.query(sql, new RowMapper<Pasta>() {
			@Override
			public Pasta mapRow(ResultSet resultSet, int i) throws SQLException {
				Pasta pasta = new Pasta();
				pasta.setId(resultSet.getLong("id"));
				pasta.setDescription(resultSet.getString("description"));
				pasta.setName(resultSet.getString("name"));
				pasta.setPrice(resultSet.getDouble("price"));
				pasta.setPictureUrl(resultSet.getString("picture"));
				return pasta;
			}
		});
		return pastas;
	}
}

