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
}
