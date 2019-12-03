package TechShopTeam4.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import TechShopTeam4.com.entities.User;
import TechShopTeam4.com.service.BaseService;

@Controller
public class BaseController {
	@Autowired
	private BaseService baseService;
	@GetMapping(value = {"/", "/home"})
	public String index(Model model) {
		model.addAttribute("products", baseService.findAllProduct());
		return "index";
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
	@GetMapping(value = {"/cart"})
	public String cart() {
		return "cart";
	}
	@GetMapping(value = {"/contact"})
	public String contact() {
		return "contact";
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
			model.addAttribute("user", tuser);
			return "redirect:/home";
		}
	}
}