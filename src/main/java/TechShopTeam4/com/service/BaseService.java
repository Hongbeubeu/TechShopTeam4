package TechShopTeam4.com.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TechShopTeam4.com.dao.TechShopDAO;
import TechShopTeam4.com.entities.*;
import TechShopTeam4.com.helper.DateTime;

@Service
@Transactional
public class BaseService {
	@Autowired
	private TechShopDAO techShopDAO;
	public User findByEmail(String email) {
		return techShopDAO.findByEmail(email);
	}
	public User login(User user) {
		User tuser = findByEmail(user.getEmail());
		if(tuser == null ) 
			return null;
		else
			try {
				if(MD5(user.getPassword()).equals(tuser.getPassword())) {
					return tuser;
				}else
					return null;
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
	}
	public User register(User user) {
		if(checkEmail(user.getEmail()) == false)
			return null;
		else {
			User tuser = findByEmail(user.getEmail());
			if(tuser == null) {
				if(user.getPassword().equals(user.getConfirmPassword()))
					try {
						user.setPassword(MD5(user.getPassword()));
						user.setCreateAt(DateTime.setDateToInt());
						techShopDAO.save(user);
						return user;
					} catch (NoSuchAlgorithmException e) {
						return null;
					}
				else {
					return null;
				}
			} else {
				return null;
			}
		}
	}
	
	public List<Laptop> findAllProduct() {
		return techShopDAO.findAllProduct();
	}
	
	private boolean checkEmail(String email) {
	    String emailPattern = "^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	    Pattern regex = Pattern.compile(emailPattern);
	    Matcher matcher = regex.matcher(email);
	    if (matcher.find()) {
	        return true;
	    } else {
	       return false;
	    }
	}
	
	private String MD5(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(text.getBytes());
        return convertByteToHex(messageDigest);
	}
	private String convertByteToHex(byte[] data) {
		BigInteger number = new BigInteger(1, data);
		String hashtext = number.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
}
