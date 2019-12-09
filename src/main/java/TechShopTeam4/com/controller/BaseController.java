package TechShopTeam4.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TechShopTeam4.com.entities.User;
import TechShopTeam4.com.service.BaseService;

@Controller
public class BaseController {
	@Autowired
	private BaseService baseService;
	
	//trang index
	@GetMapping(value = {"/", "/{userId}", "/home/{userId}"})
	public String index(Model model,
			@PathVariable(value = "userId", required = false) Integer userId) {
		model.addAttribute("products", baseService.findAllProduct());
		if(userId != null)
			model.addAttribute("user", baseService.findUserById(userId));
		return "index";
	}
	
	//chuyen den trang dang nhap
	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	
	//chuyen den trang register
	@GetMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	//xem thong tin chi tiet san pham
	@GetMapping(value = {"/product/{productId}", "/product/{userId}/{productId}"})
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
		model.addAttribute("totalPrice", baseService.totalPriceIncart(baseService.findCartByUserId(userId)));
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
			  				@RequestParam(value = "price", required = true) Integer price) {
		baseService.addToCart(userId,productId,quantity, price * quantity);
		return "redirect:/home/" + userId;
		
	}
	
	//xu ly register
	@PostMapping(value = "/doRegister")
	public String doRegister(@ModelAttribute("User") User user, 
								Model model) {
		User tuser = baseService.register(user);
		if( tuser == null)
			return "error";
		else {
			model.addAttribute("user", tuser);
			return "redirect:/home";
		}		
	}
	
	//xu ly login
	@PostMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("User") User user, 
								Model model) {
		User tuser = baseService.login(user);
		if( tuser == null)
			return "redirect:/login";
		else {
			return "redirect:/" + tuser.getId();
		}
	}
}