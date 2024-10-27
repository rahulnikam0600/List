package com.nikam.listapp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.nikam.listapp.model.Product;
import com.nikam.listapp.repository.ProductRepo;
import com.nikam.listapp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public Product saveProduct(Product product) {

		return productRepo.save(product);
		
	}

	@Override
	public List<Product> getAllProduct() {

		List<Product> products = productRepo.findAll();
		
		List<Product> updatedProducts1 = new ArrayList<Product>();
		List<Product> updatedProducts2 = new ArrayList<Product>();
		
		for(Product product: products) {
			if(!product.isStatus()) {
				updatedProducts1.add(product);
			}
		}
		Collections.sort(updatedProducts1, (p1,p2) -> p1.getName().compareTo(p2.getName()));
		for(Product product: products) {
			if(product.isStatus()) {
				updatedProducts2.add(product);
			}
		}
		Collections.sort(updatedProducts2, (p1,p2) -> p1.getName().compareTo(p2.getName()));
		
		updatedProducts1.addAll(updatedProducts2);
		
		return updatedProducts1;
		
	}

	@Override
	public boolean existProduct(String name) {
		
		return productRepo.existsByName(name);
		
	}

	@Override
	public Boolean deleteProduct(int id) {
		
		Product product = productRepo.findById(id).orElse(null);
		
		if(!ObjectUtils.isEmpty(product)) {
			productRepo.delete(product);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean updateProduct(int id ,Boolean status) {

		Product product = productRepo.findById(id).orElse(null);
		
		if(!ObjectUtils.isEmpty(product)) {
			product.setStatus(status);
			productRepo.save(product);
			return true;
		}
		
		return false;
	}

}
