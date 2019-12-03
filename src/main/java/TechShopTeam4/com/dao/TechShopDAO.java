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
		String sql = "SELECT product_id,"
				+ "name,"
				+ "chip,"
				+ "ram,"
				+ "vga,"
				+ "display,"
				+ "camera,"
				+ "hard_disk,"
				+ "keyboard,"
				+ "port,"
				+ "battery,"
				+ "opera_system "
				+ "FROM laptop_description";
		try {
			List<Laptop> laptop = jdbcTemplate.query(sql, new LaptopMapper());
			return laptop;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
