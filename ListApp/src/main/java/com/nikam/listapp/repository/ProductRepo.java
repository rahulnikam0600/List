package com.nikam.listapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikam.listapp.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	public boolean existsByName(String name);
	
}
