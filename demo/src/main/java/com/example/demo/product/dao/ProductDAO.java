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

	private static final String INSERT_PRODUCTS = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String GET_ALL_PASTAS = "SELECT * FROM dominos.products where type_category_id = 5;";
	private static final String GET_ALL_CHICKEN = "SELECT * FROM dominos.products where type_category_id = 4;";
	private static final String GET_ALL_STARTERS = "SELECT * FROM dominos.products where type_category_id = 3;";
	private static final String GET_ALL_SALADS = "SELECT * FROM dominos.products where type_category_id = 6;";
	private static final String GET_ALL_SANDWICHES = "SELECT * FROM dominos.products where type_category_id = 7;";
	private static final String GET_ALL_DESSERTS = "SELECT * FROM dominos.products where type_category_id = 9;";
	private static final String GET_ALL_CUSTOMIZE = "SELECT id, name FROM dominos.customize;";
	private static final String GET_ALL_PIZZA = "SELECT * FROM dominos.products where type_category_id = 2;";
	private static final String GET_ALL_SAUSES = "SELECT * FROM dominos.products where type_category_id = 8;";
	private static final String GET_ALL_DRINKS = "SELECT * FROM dominos.products where type_category_id = 10;";
	private static final String GET_PRODUCT_BY_ID = "SELECT * FROM dominos.products WHERE id=?;";
	private static final String REMOVE_PRODUCT = "DELETE FROM dominos.products WHERE id = ?";
	private static final String INSERT_PRODUCT_IN_DOMINOS = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_PIPS = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_SANDWICH = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_SALAD = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_PASTA = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String INSERT_CHIKEN = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String ADD_STARTER = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String ADD_DEALS = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String ADD_PIZZA = "insert into dominos.products value (?, ?, ?, ?, ?, ?, ?);";
	private static final String FET_ALL_PRODUCTS = "SELECT name FROM dominos.products;";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ProductDAO() throws URLException {
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String GET_PRODUCT_BY_ID_SQL = "SELECT * FROM dominos.product where id = ?;";

	public List<String> getAllProducts() {
		String sql = FET_ALL_PRODUCTS;

		List<String> names = jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet resultSet, int i) throws SQLException {
				return resultSet.getString(1);
			}
		});
		return names;
	}

	public int addProductPizza(Product product) {
		long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		double price = product.getPrice();
		String size = product.getSizeP();
		String pictureURL = product.getPictureUrl();
		long type_id = 2;//product type

		return jdbcTemplate.update(ADD_PIZZA,
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

		return jdbcTemplate.update(ADD_DEALS,
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

		return jdbcTemplate.update(ADD_STARTER,
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

		return jdbcTemplate.update(INSERT_CHIKEN,
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

		return jdbcTemplate.update(INSERT_PASTA,
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

		return jdbcTemplate.update(INSERT_SALAD,
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

		return jdbcTemplate.update(INSERT_SANDWICH,
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

		return jdbcTemplate.update(INSERT_PIPS,
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

		return jdbcTemplate.update(INSERT_PRODUCTS,
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

		return jdbcTemplate.update(INSERT_PRODUCT_IN_DOMINOS,
				id, name, description, price, size, pictureURL, type_id);
	}


	public boolean removeProduct(int id) {
		String sql = REMOVE_PRODUCT;
		Object[] args = new Object[] {id};

		return jdbcTemplate.update(sql, args) == 1;
	}

	@Override
	public Product getProductById(long id) throws ProductException, URLException, SQLException {
		String sql = GET_PRODUCT_BY_ID;
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
		String sql = GET_ALL_DRINKS;

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

		String sql = GET_ALL_SAUSES;

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

		String sql = GET_ALL_PIZZA;

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
		String sql = GET_ALL_CUSTOMIZE;

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
		String sql = GET_ALL_DESSERTS;

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
		String sql = GET_ALL_SANDWICHES;

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
		String sql = GET_ALL_SALADS;

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
		String sql = GET_ALL_STARTERS;

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
		String sql = GET_ALL_CHICKEN;

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
		String sql = GET_ALL_PASTAS;

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

