package com.nikam.listapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.nikam.listapp.model.Product;
import com.nikam.listapp.service.ProductService;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;

//-------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/")
	public String getProduct(Model m) {
		List<Product> products = productService.getAllProduct();
		System.out.println("getProducts");
		m.addAttribute("products",products);
		System.out.println(products);
		return "index";
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------
	
		@PostMapping("/addproduct")
		public String addProduct(@ModelAttribute Product product,
				HttpSession session) throws IOException {
			System.out.println("addProducts");
						
			boolean existProduct = productService.existProduct(product.getName());
			
			if(existProduct) {
				session.setAttribute("errorMsg","Already exist's");
			}
			else {
				Product addProduct = productService.saveProduct(product);
				System.out.println("addProduct : "+product);
				if(ObjectUtils.isEmpty(addProduct))
					session.setAttribute("errorMsg", "Not saved! Internal Server Error");
				else 								
					session.setAttribute("successMsg", "Saved sucessfully!");
			}

			return "redirect:";
		}

//-------------------------------------------------------------------------------------------------------------------------------------
		
		@PostMapping("/checkproduct/{id}")
		public String checkProduct(@PathVariable int id, @RequestParam(required = false, defaultValue = "false") Boolean status,
				HttpSession session) throws IOException {

			Boolean updated = productService.updateProduct(id,status);
			
			if(updated) {
				session.setAttribute("successMsg","Product Updated Successfully");
			}else {
				session.setAttribute("errorMsg","Something Wrong on Server");
			}

			
			return "redirect:/";
		}

//-------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable int id,
			HttpSession session) {
		
		Boolean deleteProduct = productService.deleteProduct(id);
		
		if(deleteProduct) {
			session.setAttribute("successMsg","Product Deleted Successfully");
		}else {
			session.setAttribute("errorMsg","Something Wrong on Server");
		}
		
		return "redirect:/";
		
	}

//-------------------------------------------------------------------------------------------------------------------------------------
}
