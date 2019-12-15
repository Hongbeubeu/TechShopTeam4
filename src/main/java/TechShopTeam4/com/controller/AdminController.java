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
import org.springframework.web.bind.annotation.RequestParam;
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
		model.addAttribute("users", baseService.findAllUser());
		return "admin_user";
	}
	
	@GetMapping(value = "/orders/{adminId}")
	public String orders(Model model,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("orders", baseService.findAllOrder());
		return "admin_order";
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
		model.addAttribute("product",baseService.addProduct(product));
		model.addAttribute("productImage", new MyFile());
		return "add_product_image";
	}
	
	@PostMapping(value = "/addProductImage/{adminId}/{productId}")
	public String addProductImage(MyFile myFile, 
			Model model, 
			HttpServletRequest request,
			@PathVariable(value = "productId", required = true) Integer productId,
			@PathVariable(value = "adminId", required = true) Integer adminId){
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("product", baseService.findProductById(productId));
		model.addAttribute("productImage", new MyFile());
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			String fileName = myFile.getDescription();
			baseService.addProductImage(productId, fileName);
			File file = new File("C:\\Users\\hongt\\Documents\\WEB_WORKSPACE\\TechShopTeam4.com\\src\\main\\webapp\\resources\\static\\product_images", fileName);
			multipartFile.transferTo(file);
		} catch (Exception e) {
			model.addAttribute("message", "upload failed");
			return "add_product_image";
		}
		model.addAttribute("images", baseService.findProductImageById(productId));
		return "add_product_image";
	}
	
	@GetMapping(value = "/update/{adminId}/{productId}")
	public String update(Model model,
			@PathVariable(value = "productId", required = true) Integer productId,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("product", baseService.findGeneralProductById(productId));
		return "update_product_info";
	}
	
	@PostMapping(value = "/doUpdate/{adminId}")
	public String doUpdate(Model model,
			General product,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		baseService.updateProduct(product);
		return "redirect:/admin/products/" + adminId;
	}
	
	@GetMapping(value = "/delete/{adminId}/{productId}")
	public String delete(Model model,
			@PathVariable(value = "productId", required = true) Integer productId,
			@PathVariable(value = "adminId", required = true) Integer adminId){
		baseService.deleteProduct(productId);
		return "redirect:/admin/products/" + adminId;
	}
	
	@GetMapping(value = "/addImage/{adminId}/{productId}")
	public String addImage(Model model,
			@PathVariable(value = "productId", required = true) Integer productId,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("images", baseService.findProductImageById(productId));
		model.addAttribute("product",baseService.findGeneralProductById(productId));
		model.addAttribute("productImage", new MyFile());
		return "add_product_image";
	}
	
	@PostMapping(value = "/deleteImage/{adminId}/{productId}")
	public String deleteImage(Model model,
			@RequestParam String imagePath,
			@PathVariable(value = "productId", required = true) Integer productId,
			@PathVariable(value = "adminId", required = true) Integer adminId) {
		baseService.deleteImage(imagePath);
		model.addAttribute("admin", baseService.findAdminById(adminId));
		model.addAttribute("images", baseService.findProductImageById(productId));
		model.addAttribute("product",baseService.findGeneralProductById(productId));
		model.addAttribute("productImage", new MyFile());
		return "add_product_image";
	}
	
	// test++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@SuppressWarnings("unused")
	@GetMapping(value = "/test")
	public String test(HttpServletResponse response, HttpServletRequest request, Model model) {
		
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; i< cookies.length; i++) {
			if(cookies[i].getName().equals("JSESSIONID")) {
				model.addAttribute("productImage", new MyFile());
				model.addAttribute("message", "cookie is set");
	        	return "add_product_image";
			}
			model.addAttribute("myFile", new MyFile());
			model.addAttribute("error", "cookie miss");
        	return "test";
			
		}
        	
		Cookie newCookie = new Cookie("testCookie", "hongbeubeu");
	        newCookie.setMaxAge(24 * 60 * 60);
	        response.addCookie(newCookie);
	        
		model.addAttribute("myFile", new MyFile());
		return "add_product_image";
	}
	//---------------------------------------------------------------------------------------
	
	
}
