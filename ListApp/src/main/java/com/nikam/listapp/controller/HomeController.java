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
		//System.out.println("getProducts");
		m.addAttribute("products",products);
		//System.out.println(products);
		return "index";
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------
	
		@PostMapping("/addproduct")
		public String addProduct(@ModelAttribute Product product,
				HttpSession session) throws IOException {
			productService.saveProduct(product);
			return "redirect:";
		}

//-------------------------------------------------------------------------------------------------------------------------------------
		
		@PostMapping("/checkproduct/{id}")
		public String checkProductStatus(@PathVariable int id, @RequestParam(required = false, defaultValue = "false") Boolean status,
				HttpSession session) throws IOException {
			productService.updateProductStatus(id,status);			
			return "redirect:/";
		}

//-------------------------------------------------------------------------------------------------------------------------------------

	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable int id,
			HttpSession session) {
		
		productService.deleteProduct(id);
		return "redirect:/";
		
	}

	
//-------------------------------------------------------------------------------------------------------------------------------------
	
			@GetMapping("/sortbyaz")
			public String sortByAZ() {
				productService.updateSortingLogic("AZ");		
				return "redirect:/";
			}

//-------------------------------------------------------------------------------------------------------------------------------------

			@GetMapping("/sortbyza")
			public String sortByZA() {
				productService.updateSortingLogic("ZA");		
				return "redirect:/";
			}

//-------------------------------------------------------------------------------------------------------------------------------------

			
			@GetMapping("/sortbytime")
			public String sortByTime() {
				productService.updateSortingLogic("AZ");		
				return "redirect:/";
			}

//-------------------------------------------------------------------------------------------------------------------------------------



}
