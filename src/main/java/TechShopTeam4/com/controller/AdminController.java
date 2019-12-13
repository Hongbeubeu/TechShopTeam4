package TechShopTeam4.com.controller;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import TechShopTeam4.com.entities.*;
//import TechShopTeam4.com.helper.*;
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
	
	@GetMapping(value = "/direct/{adminId}")
	public String direct(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("product", new General());
		return "add_product_form_1";
	}
	
	@PostMapping(value = "/addProduct/{adminId}")
	public String addProduct(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId,
			@ModelAttribute General product) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("product", product);
		if(product.getBrand() == null) {
			model.addAttribute("brand", baseService.findBrandByType(product.getType()));
			return "add_product_form_2";
		}
		if(product.getType().equals("laptop"))
			return "add_product_form_3"; 
		else
			return "add_product_form_1";
	}
	
	@PostMapping(value = "/add/{adminId}")
	public String add(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId,
			@ModelAttribute General product) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("product_id",baseService.addProduct(product));
		return "add_product_image";
		
	}
	
	
	// test++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@GetMapping(value = "/test")
	public String test(HttpServletResponse response, HttpServletRequest request, Model model) {
		
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i< cookies.length; i++) {
			if(cookies[i].getName().equals("JSESSIONID")) {
				model.addAttribute("myFile", new MyFile());
				model.addAttribute("error", "cookie is set");
	        	return "test";
			}
			model.addAttribute("myFile", new MyFile());
			model.addAttribute("error", "cookie miss");
        	return "test";
			
		}
        	
		Cookie newCookie = new Cookie("testCookie", "hongbeubeu");
	        newCookie.setMaxAge(24 * 60 * 60);
	        response.addCookie(newCookie);
	        
		model.addAttribute("myFile", new MyFile());
		return "test";
	}
	
	@PostMapping(value = "/uploadFile")
	public String uploadFile(MyFile myFile, Model model, HttpServletRequest request) {
		model.addAttribute("message", "upload success");
		model.addAttribute("description", myFile.getDescription());
		Cookie[] cookies = request.getCookies();
        if (cookies == null) {
        	model.addAttribute("error", "miss cookie");
        	//return "test";
        }
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			String fileName = multipartFile.getOriginalFilename();
			File file = new File("D:/files", fileName);
			multipartFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "upload failed");
		}
		//model.addAttribute("message", "upload success");
		return "test";
	}
}
