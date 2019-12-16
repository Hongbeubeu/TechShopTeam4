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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable(value = "userId", required = false) Integer userId,
			@RequestParam(value = "productName", required = false) String productName) {
		model.addAttribute("products", baseService.findAllProduct());
		if(userId != null) {
			Boolean test = false;
			HttpSession session = request.getSession();
			Cookie[] cookies = request.getCookies();
			for(int i = 0 ; i < cookies.length; i++) {
				if (cookies[i].getName().equals("SESSIONID")) {
					test = true;
					if(!cookies[i].getValue().equals(session.getId())) {
						return "redirect:/";
					}
				}
			}
			if(!test){
				return "redirect:/";
			}
			if(session.getAttribute("userId") != null) {
				User user = baseService.findUserById(userId);
				if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
					model.addAttribute("user", user);
				}else
					return "redirect:/" + session.getAttribute("userId").toString();
			}	
		}
		if(productName != null)
			model.addAttribute("search", baseService.searchProductByName(productName));
		return "index"; 
	}
/*-----------------------------------------------------------*/
//dang nhap, dang ky, dang xuat
/*-----------------------------------------------------------*/
	//chuyen den trang login
	@GetMapping(value = {"/login/{userId}", "/login"})
	public String login(Model model,
			HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable(value = "userId", required = false) Integer userId) {
		if(userId == null)
			model.addAttribute("user", new User());
		else {
			Boolean test = false;
			HttpSession session = request.getSession();
			Cookie[] cookies = request.getCookies();
			for(int i = 0 ; i < cookies.length; i++) {
				if (cookies[i].getName().equals("SESSIONID")) {
					test = true;
					if(!cookies[i].getValue().equals(session.getId())) {
						return "redirect:/login";
					}
				}
			}
			if(!test){
				return "redirect:/login";
			}
			if(session.getAttribute("userId") != null) {
				User user = baseService.findUserById(userId);
				if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
					model.addAttribute("user", user);
				}else
					return "redirect:/login" + session.getAttribute("userId").toString();
			}
		}
		return "login";
	}
	
	//xu ly login
	@PostMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("User") User user,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		User tuser = baseService.login(user);
		if( tuser == null) {
			model.addAttribute("error", "nhap email hoac mat khau khong dung");
			model.addAttribute("user", new User());
			return "login";
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("userId", tuser.getId());
			Cookie cookie = new Cookie("SESSIONID", session.getId() );
			response.addCookie(cookie);
			cookie.setMaxAge(24*60*60);
			return "redirect:/" + tuser.getId();
		}
	}

	//chuyen den trang register
	@GetMapping(value = {"/register/{userId}", "/register"})
	public String register(Model model,
			HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable(value = "userId", required = false) Integer userId) {
		if(userId == null)
			model.addAttribute("user", new User());
		else {
			Boolean test = false;
			HttpSession session = request.getSession();
			Cookie[] cookies = request.getCookies();
			for(int i = 0 ; i < cookies.length; i++) {
				if (cookies[i].getName().equals("SESSIONID")) {
					test = true;
					if(!cookies[i].getValue().equals(session.getId())) {
						return "redirect:/register";
					}
				}
			}
			if(!test){
				return "redirect:/register";
			}
			if(session.getAttribute("userId") != null) {
				User user = baseService.findUserById(userId);
				if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
					model.addAttribute("user", user);
				}else
					return "redirect:/register" + session.getAttribute("userId").toString();
			}
		}	
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
	public  String logout(HttpServletResponse response,
			HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("SESSIONID")) {
				cookies[i].setMaxAge(0);
			}
		}
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
/*-----------------------------------------------------------*/
//xu ly cac request con lai cua trang web
/*-----------------------------------------------------------*/
	//xem thong tin chi tiet san pham
	@GetMapping(value = {"/product/{productId}", "/product/{userId}/{productId}"})
	public String product(Model model, 
			HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable(value = "productId", required = false) Integer productId,
			@PathVariable(value = "userId", required = false) Integer userId) {
		if(userId != null) {
			Boolean test = false;
			HttpSession session = request.getSession();
			Cookie[] cookies = request.getCookies();
			for(int i = 0 ; i < cookies.length; i++) {
				if (cookies[i].getName().equals("SESSIONID")) {
					test = true;
					if(!cookies[i].getValue().equals(session.getId())) {
						return "redirect:/";
					}
				}
			}
			if(!test){
				return "redirect:/";
			}
			if(session.getAttribute("userId") != null) {
				User user = baseService.findUserById(userId);
				if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
					model.addAttribute("user", user);
				}else
					return "redirect:/" + session.getAttribute("userId").toString();
				model.addAttribute("user", user);
			}
			
		}
		model.addAttribute("images", baseService.findProductImageById(productId));
		model.addAttribute("product", baseService.findProductById(productId));
		return "product";
	}
	
	//xem san pham co trong cart
	@GetMapping(value = "/cart/{userId}")
	public String cart(@PathVariable(value = "userId") Integer userId,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		Boolean test = false;
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i < cookies.length; i++) {
			if (cookies[i].getName().equals("SESSIONID")) {
				test = true;
				if(!cookies[i].getValue().equals(session.getId())) {
					return "redirect:/";
				}
			}
		}
		if(!test){
			return "redirect:/";
		}
		if(session.getAttribute("userId") != null) {
			User user = baseService.findUserById(userId);
			if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
				model.addAttribute("user", user);
			}else
				return "redirect:/" + session.getAttribute("userId").toString();
			model.addAttribute("user", user);
		}
		model.addAttribute("cart", baseService.findCartByUserId(userId));
		model.addAttribute("totalPrice", baseService.totalPriceInCart(baseService.findCartByUserId(userId)));
		return "cart";
	}
	
	//thong tin ca nhan user
	@GetMapping(value = {"/profile/{userId}"})
	public String profile(Model model,
			HttpServletResponse response,
			HttpServletRequest request,
			@PathVariable("userId") int userId) {
		Boolean test = false;
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i < cookies.length; i++) {
			if (cookies[i].getName().equals("SESSIONID")) {
				test = true;
				if(!cookies[i].getValue().equals(session.getId())) {
					return "redirect:/";
				}
			}
		}
		if(!test){
			return "redirect:/";
		}
		if(session.getAttribute("userId") != null) {
			User user = baseService.findUserById(userId);
			if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
				model.addAttribute("user", user);
			}else
				return "redirect:/profile/" + session.getAttribute("userId").toString();
			model.addAttribute("user", user);
		}
		return "profile";
	}
	
	//them san pham vao cart
	@PostMapping(value = "/addToCart")
	public String addToCart(@RequestParam(value = "userId", required = true) Integer userId, 
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
				HttpServletResponse response,
				HttpServletRequest request,
				Model model) {
		Boolean test = false;
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i < cookies.length; i++) {
			if (cookies[i].getName().equals("SESSIONID")) {
				test = true;
				if(!cookies[i].getValue().equals(session.getId())) {
					return "redirect:/";
				}
			}
		}
		if(!test){
			return "redirect:/";
		}
		if(session.getAttribute("userId") != null) {
			User user = baseService.findUserById(userId);
			if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
				model.addAttribute("user", user);
			}else
				return "redirect:/cart/" + session.getAttribute("userId").toString();
		}
		baseService.deleteCart(userId, productId);
		return "redirect:/cart/" + userId;
		
	}
	
	//thanh toan
	@GetMapping(value = "/pay/{userId}")
	public String pay(@PathVariable(value = "userId", required = true) Integer userId,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		Boolean test = false;
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i < cookies.length; i++) {
			if (cookies[i].getName().equals("SESSIONID")) {
				test = true;
				if(!cookies[i].getValue().equals(session.getId())) {
					return "redirect:/";
				}
			}
		}
		if(!test){
			return "redirect:/";
		}
		if(session.getAttribute("userId") != null) {
			User user = baseService.findUserById(userId);
			if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
				model.addAttribute("user", user);
			}else
				return "redirect:/pay/" + session.getAttribute("userId").toString();
			model.addAttribute("user", user);
		}
		model.addAttribute("delivery", new Delivery());
		return "pay";
	}
	
	//xu ly form van chuyen
	@PostMapping(value = "/doPay/{userId}")
	public String doPay(@ModelAttribute("delivery") Delivery delivery,
			@PathVariable(value = "userId", required = true) Integer userId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("user", baseService.findUserById(userId));
		delivery.setOrderId(baseService.addToOrder(userId));
		baseService.fillFormDelivery(delivery);
		return "redirect:/profile/" + userId;
	}
	
	//purchased
	@GetMapping(value = "/purchased/{userId}")
	public String purchased(@PathVariable(value = "userId") Integer userId,
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) {
		Boolean test = false;
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i < cookies.length; i++) {
			if (cookies[i].getName().equals("SESSIONID")) {
				test = true;
				if(!cookies[i].getValue().equals(session.getId())) {
					return "redirect:/";
				}
			}
		}
		if(!test){
			return "redirect:/";
		}
		if(session.getAttribute("userId") != null) {
			User user = baseService.findUserById(userId);
			if(String.valueOf(user.getId()).equals(session.getAttribute("userId").toString())) {
				model.addAttribute("user", user);
			}else
				return "redirect:/" + session.getAttribute("userId").toString();
			model.addAttribute("user", user);
		}
		model.addAttribute("purchased", baseService.findOrderByUserId(userId));
		model.addAttribute("userId", userId);
		return "purchased";
	}
/*-----------------------------------------------------------*/
}