package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Cart;

public class CartMapper implements RowMapper<Cart>{
	public Cart mapRow(ResultSet rs, int rowNum) throws SQLException{
		Cart cart = new Cart();
		cart.setUserId(rs.getInt("customer_id"));
		cart.setProductId(rs.getInt("product_id"));
		cart.setName(rs.getString("name"));
		cart.setImgPath(rs.getString("image_path"));
		cart.setQuantity(rs.getInt("quantity"));
		cart.setTotalPrice(rs.getInt("total_price"));
		cart.setStatus(rs.getString("status"));
		return cart;
	}
}
