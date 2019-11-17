package TechShopTeam4.com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
	@GetMapping(value = {"/", "/home"})
	public String index() {
		return "index";
	}
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
	@GetMapping(value = "/register")
	public String register() {
		return "register";
	}
}