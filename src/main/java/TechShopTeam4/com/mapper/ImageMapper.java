package TechShopTeam4.com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import TechShopTeam4.com.entities.Image;

public class ImageMapper implements RowMapper<Image>{
	public Image mapRow(ResultSet rs, int rowNum) throws SQLException{
		Image image = new Image();
		image.setImgPath(rs.getString("image_path"));
		return image;
	}
}
