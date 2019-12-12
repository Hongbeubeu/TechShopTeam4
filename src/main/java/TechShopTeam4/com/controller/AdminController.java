package TechShopTeam4.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import TechShopTeam4.com.entities.*;
import TechShopTeam4.com.helper.*;
import TechShopTeam4.com.service.BaseService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private BaseService baseService;
	
	// chuyen den trang admin login
	@GetMapping(value = "/login")
	public String adminLogin(Model model) {
		model.addAttribute("admin", new User());
		return "admin_login";
	}
		
	//xu ly admin login
	@PostMapping(value = {"/adminDoLogin"})
	public String adminDoLogin(Model model,
			@ModelAttribute User admin) {
		
		User tadmin = baseService.adminLogin(admin);
		if( tadmin == null) {
			model.addAttribute("error", "nhap email hoac mat khau khong dung");
			model.addAttribute("admin", new User());
			return "admin_login";
		}
		else {
			return "redirect:/admin/" + tadmin.getId() ;
		}
	}
	
	//dashboard
	@GetMapping(value = {"/{adminId}"})
	public String admin(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("sales", baseService.findSale());
		model.addAttribute("countOrders", baseService.countOrder());
		return "admin";
	}
	
	//manage products
	@GetMapping(value = "/products/{adminId}")
	public String products(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("products", baseService.findAllManageProduct());
		return "admin_product";
	}
	
	//manage user
	@GetMapping(value = "/users/{adminId}")
	public String users(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		return "admin_user";
	}
	
	
	@GetMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("month", Currency.formatCurrency(1111));
		return "test";
	}
}
