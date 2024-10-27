package com.nikam.listapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nikam.listapp.model.Product;

@Service
public interface ProductService {

	public Product saveProduct(Product product);
	
	public List<Product> getAllProduct();
	
	public boolean existProduct(String name);

	public Boolean deleteProduct(int id);

	public Boolean updateProduct(int id, Boolean status);
}
