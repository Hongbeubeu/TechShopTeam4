package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.DeliveryBill;
import TechShopTeam4.com.helper.Currency;
import TechShopTeam4.com.helper.DateTime;

public class DeliveryBillMapper implements RowMapper<DeliveryBill> {
	public DeliveryBill mapRow(ResultSet rs, int rowNum) throws SQLException {
		DeliveryBill deliveryBill = new DeliveryBill();
		deliveryBill.setOrderId(rs.getInt("order_id"));
		deliveryBill.setProductId(rs.getInt("id"));
		deliveryBill.setProductName(rs.getString("name"));
		deliveryBill.setFirstName(rs.getString("first_name"));
		deliveryBill.setLastName(rs.getString("last_name"));
		deliveryBill.setPhoneNumber(rs.getString("phone_number"));
		deliveryBill.setAddress(rs.getString("address"));
		deliveryBill.setQuantity(rs.getInt("quantity"));
		deliveryBill.setIntPrice(rs.getInt("price"));
		deliveryBill.setPrice(Currency.formatCurrency(deliveryBill.getQuantity() * deliveryBill.getIntPrice()));
		deliveryBill.setDate(DateTime.setIntToDate(rs.getInt("create_at")).toString());
		return deliveryBill;
	}
}
