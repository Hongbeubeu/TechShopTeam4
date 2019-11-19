package TechShopTeam4.com.mapper;

import java.sql.ResultSet;import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.User;

public class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setAddress(rs.getString("address"));
		user.setPassword(rs.getString("password"));
		user.setCreateAt(rs.getInt("create_at"));
		return user;
	}
}
