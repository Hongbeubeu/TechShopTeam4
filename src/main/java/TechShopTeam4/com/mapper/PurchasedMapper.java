package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Purchased;
import TechShopTeam4.com.helper.Currency;
import TechShopTeam4.com.helper.DateTime;

public class PurchasedMapper implements RowMapper<Purchased>{
	public Purchased mapRow(ResultSet rs, int rowNum) throws SQLException{
		Purchased purchased = new Purchased();
		purchased.setOrderId(rs.getInt("id"));
		purchased.setName(rs.getString("name"));
		purchased.setProductId(rs.getInt("product_id"));
		purchased.setName(rs.getString("name"));
		purchased.setQuantity(rs.getInt("quantity"));
		purchased.setIntPrice(rs.getInt("price"));
		purchased.setImgPath(rs.getString("image_path"));
		purchased.setPrice(Currency.formatCurrency(purchased.getQuantity() * purchased.getIntPrice()));
		purchased.setStatus(rs.getString("status"));
		purchased.setDate(DateTime.setIntToDate(rs.getInt("create_at")).toString());
		return purchased;
	}
}
