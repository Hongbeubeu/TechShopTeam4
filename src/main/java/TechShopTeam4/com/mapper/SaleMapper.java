package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Sale;
import TechShopTeam4.com.helper.Currency;

public class SaleMapper implements RowMapper<Sale> {
	public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sale sale = new Sale();
		sale.setIntPrice(rs.getInt("total_price"));
		sale.setPrice(Currency.formatCurrency(sale.getIntPrice()));
		return sale;
	}
}
