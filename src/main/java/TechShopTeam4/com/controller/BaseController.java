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
	@GetMapping(value = {"/", "/home"})
	public String index(Model model) {
		model.addAttribute("products", baseService.findAllProduct());
		return "test";
	}
	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}
	@GetMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	@GetMapping(value = {"/products"})
	public String product() {
		return "products";
	}
	@GetMapping(value = {"/cart/{userId}"})
	public String cart(@PathVariable("userId") int userId, Model model) {
		model.addAttribute("cart", baseService.findCartByUserId(userId));
		return "cart";
	}
	@GetMapping(value = {"/contact"})
	public String contact() {
		return "contact";
	}
	@GetMapping(value = {"/profile/{id}"})
	public String profile(Model model,
							@PathVariable("id") int id) {
		model.addAttribute("user", baseService.findUserById(id));
		return "profile";
	}
	@PostMapping(value = "/addToCart")
	public String addTocart(@RequestParam(value = "userId", required = true) Integer userId, 
			  				@RequestParam(value = "productId", required = true) Integer productId, 
			  				@RequestParam(value = "quantity", required = true) Integer quantity,
			  				@RequestParam(value = "price", required = true) Integer price) {
		baseService.addToCart(userId,productId,quantity, price * quantity);
		return "cart";
		
	}
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
	@PostMapping(value = "/doLogin")
	public String doLogin(@ModelAttribute("User") User user, 
								Model model) {
		User tuser = baseService.login(user);
		if( tuser == null)
			return "redirect:/login";
		else {
			model.addAttribute("product", baseService.findProductById(26));
			model.addAttribute("images", baseService.findProductImageById(26));
			model.addAttribute("user", tuser);
			return "product";
		}
	}
}