package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.General;

public class GeneralMapper implements RowMapper<General>{
	public General mapRow(ResultSet rs, int rowNum) throws SQLException{
		General product = new General();
		product.setProductId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setChip(rs.getString("chip"));
		product.setRam(rs.getString("ram"));
		product.setVga(rs.getString("vga"));
		product.setDisplay(rs.getString("display"));
		product.setCamera(rs.getString("camera"));
		product.setHardDisk(rs.getString("hard_disk"));
		product.setKeyboard(rs.getString("keyboard"));
		product.setPort(rs.getString("port"));
		product.setBattery(rs.getString("battery"));
		product.setOperaSystem(rs.getString("opera_system"));
		product.setQuantity(rs.getInt("quantity"));
		product.setPrice(rs.getInt("price"));
		return product;
	}
}
