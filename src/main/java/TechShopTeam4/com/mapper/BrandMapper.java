package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Brand;

public class BrandMapper implements RowMapper<Brand>{
	public Brand mapRow(ResultSet rs, int rowNum) throws SQLException{
		Brand brand = new Brand();
		brand.setBrand(rs.getString("name"));
		return brand;
	}
}
