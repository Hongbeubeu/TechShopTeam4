package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.NumberInt;

public class NumberIntMapper implements RowMapper<NumberInt>{
	public NumberInt mapRow(ResultSet rs, int rowNum) throws SQLException{
		NumberInt numberInt = new NumberInt();
		numberInt.setNum(rs.getInt("id"));
		return numberInt;
	}
}
