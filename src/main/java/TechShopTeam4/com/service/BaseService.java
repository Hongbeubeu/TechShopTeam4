package TechShopTeam4.com.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
	
	public User adminLogin(User	admin) {
		User tadmin = techShopDAO.findAdminByEmail(admin.getEmail());
		if(tadmin == null)
			return null;
		else
			try {
				if(MD5(admin.getPassword()).equals(tadmin.getPassword())) {
					return tadmin;
				}else
					return null;
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
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
						int userRole = techShopDAO.findByEmail(user.getEmail()).getId();
						techShopDAO.addRoleUser(userRole);
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
	
	public Laptop findProductById(int id) {
		return techShopDAO.findProductById(id);
	}
	public List<Image> findProductImageById(int id) {
		return techShopDAO.findProductImageById(id);
	}
	
	public boolean addToCart(int userId, int productId, int quantity, int totalPrice) {
		Cart cart = techShopDAO.findProductInCart(userId, productId);
		if(cart != null) {
			quantity += cart.getQuantity();
			if(quantity > techShopDAO.findProductById(productId).getQuantity())
				return false;
			totalPrice += cart.getTotalPrice();
			techShopDAO.addToExistCart(userId, productId, quantity, totalPrice);
			return true;
		}
		String status = "inCart";
		int createAt = DateTime.setDateToInt();
		techShopDAO.addToCart(userId, productId, quantity, totalPrice, status, createAt);
		return true;
	}
	
	public void deleteCart(int userId, int productId) {
		techShopDAO.deleteCart(userId, productId);
	}
	
	public User findUserById(int id) {
		return techShopDAO.findUserById(id);
	}
	
	public User findAdminById(int id) {
		return techShopDAO.findAdminById(id);
	}
	
	public List<Cart> findCartByUserId(int userId) {
		return techShopDAO.findCartByUserId(userId);
	}
	
	public int totalPriceInCart(List<Cart> cart) {
		int totalPrice = 0;
		for(Cart c: cart) {
			totalPrice += c.getTotalPrice();
		}
		return totalPrice;
	}
	
	public List<Laptop> searchProductByName(String productName) {
		productName = "%" + productName + "%";
		return techShopDAO.searchProductByName(productName);
	}
	
	public int addToOrder(int userId) {
		List<Cart> cart = findCartByUserId(userId);
		int totalPrice = totalPriceInCart(cart);
		int date = DateTime.setDateToInt();
		techShopDAO.addToOrder(userId, totalPrice, date);
		int orderId = techShopDAO.findOrderIdByCreateAt(date).getId();
		for(Cart c: cart) {
			deleteCart(userId, c.getProductId());
			techShopDAO.addOrderDetail(orderId, c.getProductId(), c.getQuantity(), "inorder", DateTime.setDateToInt());
		}
		return orderId;
	}
	
	public void fillFormDelivery(Delivery delivery) {
		techShopDAO.fillFormDelivery(delivery, DateTime.setDateToInt());
	}
	
	public List<Purchased> findOrderByUserId(int userId){
		return techShopDAO.findOrderByUserId(userId);
	}
	
	public List<ManageProduct> findAllManageProduct(){
		return techShopDAO.findAllManageProduct();
	}
	
	public List<Sale> findSale(){
		List<Sale> sale = new ArrayList<Sale>();
		for(int month = 1; month <= 12 ; month++) {
			Sale tsale = techShopDAO.findSaleByMonth(month);
			if(tsale == null) {
				Sale usale = new Sale();
				usale.setPrice("0 đ");
				sale.add(usale);
			}else	
				sale.add(tsale);
		}
		return sale;
	}
	
	public List<Order> countOrder(){
		List<Order> count = new ArrayList<Order>();
		for(int month = 1; month <= 12; month ++) {
			Order order = techShopDAO.countOrder(month);
			if(order == null) {
				Order torder = new Order();
				torder.setId(0);
				count.add(torder);
			}else
				count.add(order);
		}
		return count;
	}
	
	public List<Brand> findBrandByType(String type){
		return techShopDAO.findBrandByType(type);
	}
	
	public General addProduct(General product) {
		int date = DateTime.setDateToInt();
		int parentId = techShopDAO.findParent(product.getBrand(), product.getType());
		techShopDAO.addProductToCategory(product.getName(), parentId, date);
		int id = techShopDAO.findProductByCreatAt(date);
		int desId = 0;
		if(product.getType().equals("laptop")) {
			desId = 1;
			techShopDAO.addProductToProduct(id, desId, product.getQuantity(), product.getPrice(), date );
			techShopDAO.addLaptopDescription(id, product, date);	
		}else if (product.getType().equals("camera")) {
			desId = 2;
			techShopDAO.addProductToProduct(id, desId, product.getQuantity(), product.getPrice(), date );
			//techShopDAO.addCameraDescription(id, product, date);	
		}else {
			desId = 3;
			techShopDAO.addProductToProduct(id, desId, product.getQuantity(), product.getPrice(), date );
			//techShopDAO.addSmartPhoneDescription(id, product, date);	
		}
		product.setProductId(id);
		return product;
	}
	
	public void addProductImage(int id, String name) {
		int createAt = DateTime.setDateToInt();
		techShopDAO.addProductImage(id, name, createAt);
	}
	
	public General findGeneralProductById(int productId) {
		return techShopDAO.findGeneralProductById(productId);
	}
	
	public void updateProduct(General product) {
		techShopDAO.updateProduct(product, DateTime.setDateToInt());
	}
	
	public void deleteProduct(int productId) {
		techShopDAO.deleteProduct(productId);
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
