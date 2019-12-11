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
	
	public void addRoleUser(int userRole) {
		String sql = "INSERT INTO  user_role "
				+ "(user_id, role ) "
				+ "VALUES (?, ?) ";
		jdbcTemplate.update(sql, userRole, 2);
				
	}
	
	public User findByEmail(String email) {
		String sql = "SELECT * "
				+ "FROM user "
				+ "WHERE email = ?";
		try {
			User user =  jdbcTemplate.queryForObject(sql, new UserMapper(), email);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findAdminByEmail(String email) {
		String sql = "SELECT u.id, u.email, u.password, u.phone_number, u.address, u.create_at "
				+ "FROM user u, role r, user_role ur  "
				+ "WHERE u.email = ? "
				+ "AND u.id = ur.user_id "
				+ "AND ur.role = r.id "
				+ "AND r.name = ?";
		try {
			User admin =  jdbcTemplate.queryForObject(sql, new UserMapper(), email, "ROLE_ADMIN");
			return admin;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findAdminById(int id) {
		String sql = "SELECT u.id, u.email, u.password, u.phone_number, u.address, u.create_at "
				+ "FROM user u, role r, user_role ur  "
				+ "WHERE u.id = ? "
				+ "AND u.id = ur.user_id "
				+ "AND ur.role = r.id "
				+ "AND r.name = ?";
		try {
			User admin =  jdbcTemplate.queryForObject(sql, new UserMapper(), id, "ROLE_ADMIN");
			return admin;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findUserById(int id) {
		String sql = "SELECT * "
				+ "FROM user "
				+ "WHERE id = ?";
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
		String sql = "DELETE FROM cart "
				+ "WHERE customer_id = ? "
				+ "AND product_id = ? ";
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
				+ "FROM cart c, "
				+ "product_image i, "
				+ "laptop_description l "
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
	
	public List<Laptop> searchProductByName(String productName){
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
				+ "WHERE ld.name LIKE ? "
				+ "AND ld.product_id = p.id "
				+ "AND ld.product_id = pi.product_id "
				+ "GROUP BY ld.product_id";
		try {
			List<Laptop> laptop = jdbcTemplate.query(sql, new LaptopMapper(), productName); 
			return laptop;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void addToOrder(int userId, int totalPrice, int createAt) {
		String sql = "INSERT INTO `order` "
				+ "(customer_id, total_price, payment_method, status, create_at) "
				+ "VALUES (?,?,?,?,?)";
		String paymentMethod = "ttknh";
		String status = "inorder";
		jdbcTemplate.update(sql, userId, totalPrice, paymentMethod, status, createAt);
	}
	
	public Order findOrderIdByCreateAt(int createAt) {
		String sql = "SELECT id "
				+ "FROM `order` "
				+ "WHERE create_at = ? "
				+ "AND status = ?";
		try {
			Order order = jdbcTemplate.queryForObject(sql, new OrderIdMapper(), createAt, "inorder");
			return order;
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void addOrderDetail(int orderId, int productId, int quantity, String status, int createAt) {
		String sql = "INSERT INTO order_detail "
				+ "(order_id, product_id, quantity, status, create_at) "
				+ "VALUES (?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, orderId, productId, quantity, status, createAt);
	}
	
	public void fillFormDelivery(Delivery delivery, int createAt) {
		String sql = "INSERT INTO `delivery` "
				+ "(order_id, first_name, last_name, phone_number, address, create_at) "
				+ "VALUES (?, ?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sql, 
				delivery.getOrderId(), 
				delivery.getFirstName(), 
				delivery.getLastName(),
				delivery.getPhoneNumber(),
				delivery.getAddress(),
				createAt);
	}
	
	public List<Order> findOrderByUserId(int userId){
		String sql = "SELECT id, total_price, status "
				+ "FROM `order` "
				+ "WHERE customer_id = ? ";
		try {
			List<Order> order = jdbcTemplate.query(sql, new OrderMapper(), userId);
			return order;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
