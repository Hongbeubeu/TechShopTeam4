package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Laptop;

public class LaptopMapper implements RowMapper<Laptop>{
	public Laptop mapRow(ResultSet rs, int rowNum) throws SQLException{
		Laptop laptop = new Laptop();
		laptop.setProductId(rs.getInt("product_id"));
		laptop.setName(rs.getString("name"));
		laptop.setChip(rs.getString("chip"));
		laptop.setRam(rs.getString("ram"));
		laptop.setVga(rs.getString("vga"));
		laptop.setDisplay(rs.getString("display"));
		laptop.setCamera(rs.getString("camera"));
		laptop.setHardDisk(rs.getString("hard_disk"));
		laptop.setKeyboard(rs.getString("keyboard"));
		laptop.setPort(rs.getString("port"));
		laptop.setBattery(rs.getString("battery"));
		laptop.setOperaSystem(rs.getString("opera_system"));
		return laptop;
	}
}
