package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.ManageProduct;
import TechShopTeam4.com.helper.Currency;

public class ManageProductMapper implements RowMapper<ManageProduct>{
	public ManageProduct mapRow(ResultSet rs, int rowNum) throws SQLException{
		ManageProduct product = new ManageProduct();
		product.setId(rs.getInt("product_id"));
		product.setName(rs.getString("name"));
		product.setPrice(Currency.formatcurrency(rs.getInt("price")));
		product.setQuantity(rs.getInt("quantity"));
		return product;
	}
}
