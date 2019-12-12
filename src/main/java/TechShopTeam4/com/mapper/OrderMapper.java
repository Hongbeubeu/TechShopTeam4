package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Order;

public class OrderMapper implements RowMapper<Order>{
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
		Order order = new Order();
		order.setId(rs.getInt("id"));
		//order.setTotalPrice(rs.getInt("total_price"));
		//order.setStatus(rs.getString("status"));
		return order;
	}
}
