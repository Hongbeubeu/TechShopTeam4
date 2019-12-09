package TechShopTeam4.com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import TechShopTeam4.com.entities.*;
import TechShopTeam4.com.mapper.*;

@Repository
@Transactional
public class TechShopDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(User user) {
		String sql =  "INSERT INTO user "
					+ "(email, phone_number, address, password, create_at) "
					+ "VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,user.getEmail(), 
								user.getPhoneNumber(),
								user.getAddress(),
								user.getPassword(),
								user.getCreateAt()
							);
	}
	
	public User findByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email = ?";
		try {
			User user =  jdbcTemplate.queryForObject(sql, new UserMapper(), email);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findUserById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			User user =  jdbcTemplate.queryForObject(sql, new UserMapper(), id);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Laptop> findAllProduct() {
		String sql = "SELECT ld.product_id,"
				+ "ld.name,"
				+ "ld.chip,"
				+ "ld.ram,"
				+ "ld.vga,"
				+ "ld.display,"
				+ "ld.camera,"
				+ "ld.hard_disk,"
				+ "ld.keyboard,"
				+ "ld.port,"
				+ "ld.battery,"
				+ "ld.opera_system,"
				+ "p.quantity,"
				+ "p.price,"
				+ "pi.image_path "
				+ "FROM laptop_description ld,"
				+ "product p," 
				+ "product_image pi "
				+ "WHERE ld.product_id = p.id "
				+ "AND ld.product_id = pi.product_id "
				+ "GROUP BY ld.product_id";
		try {
			List<Laptop> laptop = jdbcTemplate.query(sql, new LaptopMapper());
			return laptop;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Laptop findProductById(int id) {
		String sql = "SELECT ld.product_id,"
				+ "ld.name,"
				+ "ld.chip,"
				+ "ld.ram,"
				+ "ld.vga,"
				+ "ld.display,"
				+ "ld.camera,"
				+ "ld.hard_disk,"
				+ "ld.keyboard,"
				+ "ld.port,"
				+ "ld.battery,"
				+ "ld.opera_system,"
				+ "p.quantity,"
				+ "p.price "
				+ "FROM laptop_description ld,"
				+ "product p "
				+ "WHERE ld.product_id = ? "
				+ "AND ld.product_id = p.id "
				+ "LIMIT 1";
		try {
			Laptop laptop =jdbcTemplate.queryForObject(sql, new OneLaptopMapper(), id);
			return laptop;
		} catch (EmptyResultDataAccessException e) {
			return null;		
		}
	}
	public Cart findProductInCart(int userId, int productId) {
		String sql = "SELECT customer_id,"
				+ "product_id,"
				+ "quantity,"
				+ "total_price "
				+ "FROM cart "
				+ "WHERE customer_id = ? "
				+ "AND product_id = ? "
				+ "AND status = ? ";
		try {
			Cart cart = jdbcTemplate.queryForObject(sql, new CheckCartMapper(), userId, productId, "incart");
			return cart;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	public void deleteCart(int userId, int productId) {
		String sql = "DELETE FROM cart WHERE customer_id = ? AND product_id = ? ";
		jdbcTemplate.update(sql, userId, productId);
	}
	public List<Image> findProductImageById(int id){
		String sql = "SELECT image_path "
				+ "FROM product_image "
				+ "WHERE product_id = ?";
		try {
			List<Image> image = jdbcTemplate.query(sql, new ImageMapper(), id);
			return image;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void addToCart(int userId, int productId, int quantity, int totalPrice, String status, int createAt) {
		String sql = "INSERT INTO cart "
				+ "(customer_id, product_id, quantity, total_price, status, create_at) "
				+ "	VALUES (?, ?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, userId, productId, quantity, totalPrice, status, createAt);
	}
	
	public void addToExistCart(int userId, int productId, int quantity, int totalPrice) {
		String sql = "UPDATE cart "
				+ "SET quantity = ?,"
				+ "total_price = ? "
				+ "WHERE customer_id = ? "
				+ "AND product_id = ? ";
		jdbcTemplate.update(sql, quantity, totalPrice, userId, productId);
	}
	public List<Cart> findCartByUserId(int userId) {
		String sql = "SELECT c.customer_id,"
				+ "c.product_id,"
				+ "c.quantity,"
				+ "c.total_price,"
				+ "c.status,"
				+ "l.name,"
				+ "i.image_path "
				+ "FROM cart c, product_image i, laptop_description l "
				+ "WHERE c.customer_id = ? "
				+ "AND c.status = ? "
				+ "AND c.product_id = i.product_id "
				+ "AND c.product_id = l.product_id "
				+ "GROUP BY l.name";
		try {
			List<Cart> cart = jdbcTemplate.query(sql, new CartMapper(), userId, "incart");
			return cart;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
