/*-----------------------------------------------------------*/
/*
 * index
 */
/*-----------------------------------------------------------*/
/*
 *dang nhap, dang ky, dang xuat
 */
/*-----------------------------------------------------------*/
/*
 * xu ly cac request con lai cua trang web
 */
/*-----------------------------------------------------------*/


package TechShopTeam4.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TechShopTeam4.com.entities.*;
import TechShopTeam4.com.service.BaseService;

@Controller
public class BaseController {
	@Autowired
	private BaseService baseService;
	
//index
/*-----------------------------------------------------------*/
	//trang index
	@GetMapping(value = {"/", "/{userId}"})
	public String index(Model model,
			@PathVariable(value = "userId", required = false) Integer userId,
			@RequestParam(value = "productName", required = false) String productName) {
		model.addAttribute("products", baseService.findAllProduct());
		if(userId != null)
			model.addAttribute("user", baseService.findUserById(userId));
		if(productName != null)
			model.addAttribute("search", baseService.searchProductByName(productName));
		return "index"; 
	}
/*-----------------------------------------------------------*/
//dang nhap, dang ky, dang xuat
/*-----------------------------------------------------------*/
	//chuyen den trang login
	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	//xu ly login
	@PostMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("User") User user, 
								Model model) {
		User tuser = baseService.login(user);
		if( tuser == null) {
			model.addAttribute("error", "nhap email hoac mat khau khong dung");
			model.addAttribute("user", new User());
			return "login";
		}
		else {
			return "redirect:/" + tuser.getId();
		}
	}

	//chuyen den trang register
	@GetMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	//xu ly register
	@PostMapping(value = "/doRegister")
	public String doRegister(@ModelAttribute("User") User user, 
								Model model) {
		User tuser = baseService.register(user);
		if( tuser == null) {
			if(baseService.findByEmail(user.getEmail()) != null)
				model.addAttribute("email_error", "email da ton tai");
			//model.addAttribute("error", "nhap sai thong tin");
			model.addAttribute("user", new User());
			if(!user.getPassword().equals(user.getConfirmPassword()))
				model.addAttribute("password_error", "nhap password khong khop");
			return "register";
		}
		else {
			return "success";
		}		
	}
		
	//log out
	@GetMapping(value = "/logout")
	public  String logout() {
		return "redirect:/";
	}
	
/*-----------------------------------------------------------*/
//xu ly cac request con lai cua trang web
/*-----------------------------------------------------------*/
	//xem thong tin chi tiet san pham
	@GetMapping(value = {"/product/{productId}", "/{userId}/product/{productId}"})
	public String product(Model model, 
			@PathVariable(value = "productId", required = false) Integer productId,
			@PathVariable(value = "userId", required = false) Integer userId) {
		if(userId != null) 
			model.addAttribute("user", baseService.findUserById(userId));
		model.addAttribute("images", baseService.findProductImageById(productId));
		model.addAttribute("product", baseService.findProductById(productId));
		return "product";
	}
	
	//xem san pham co trong cart
	@GetMapping(value = {"/cart/{userId}"})
	public String cart(@PathVariable("userId") int userId,
			Model model) {
		model.addAttribute("user", baseService.findUserById(userId));
		model.addAttribute("cart", baseService.findCartByUserId(userId));
		model.addAttribute("totalPrice", baseService.totalPriceInCart(baseService.findCartByUserId(userId)));
		return "cart";
	}
	
	//thong tin ca nhan user
	@GetMapping(value = {"/profile/{id}"})
	public String profile(Model model,
							@PathVariable("id") int id) {
		model.addAttribute("user", baseService.findUserById(id));
		return "profile";
	}
	
	//them san pham vao cart
	@PostMapping(value = "/addToCart")
	public String addTocart(@RequestParam(value = "userId", required = true) Integer userId, 
			  				@RequestParam(value = "productId", required = true) Integer productId, 
			  				@RequestParam(value = "quantity", required = true) Integer quantity,
			  				@RequestParam(value = "price", required = true) Integer price,
			  				Model model) {
		if(quantity > baseService.findProductById(productId).getQuantity()){
			model.addAttribute("error", "so luong san pham trong kho it hon so luong khach yeu cau");
			return "error";
		}
		if(baseService.addToCart(userId,productId,quantity, price * quantity))
			return "redirect:/cart/" + userId;
		else {
			model.addAttribute("error", "so luong san pham trong kho it hon so luong khach yeu cau");
			return "error";
		}
	}
	
	//delte san pham khoi cart
	@GetMapping(value = "/deleteCart/{userId}/{productId}")
	public String deleteCart(@PathVariable(value = "userId", required = true) Integer userId, 
				@PathVariable(value = "productId", required = true) Integer productId,
				Model model) {
		baseService.deleteCart(userId, productId);
		return "redirect:/cart/" + userId;
		
	}
	
	//thanh toan
	@PostMapping(value = "/pay/{userId}")
	public String pay(@PathVariable(value = "userId", required = true) Integer userId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("user", baseService.findUserById(userId));
		model.addAttribute("orderId", baseService.addToOrder(userId));
		model.addAttribute("delivery", new Delivery());
		return "pay";
	}
	
	//xu ly form van chuyen
	@PostMapping(value = "/doPay/{userId}")
	public String doPay(@ModelAttribute("delivery") Delivery delivery,
			@PathVariable(value = "userId", required = true) Integer userId,
			Model model) {
		baseService.fillFormDelivery(delivery);
		return "redirect:/" + userId;
	}
	
	//purchased
	@GetMapping(value = "/purchased/{userId}")
	public String purchased(@PathVariable(value = "userId") Integer userId,
			Model model) {
		model.addAttribute("purchased", baseService.findOrderByUserId(userId));
		model.addAttribute("user", baseService.findUserById(userId));
		model.addAttribute("userId", userId);
		return "purchased";
	}
/*-----------------------------------------------------------*/
}