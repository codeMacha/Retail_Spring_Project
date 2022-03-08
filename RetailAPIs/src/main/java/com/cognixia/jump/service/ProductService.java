package com.cognixia.jump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repo;
	
	public List<Product>getAllProducts() {
		return repo.findAll();
	}
	
	public Product createProduct(Product product) {
		Product created = repo.save(product);
		return created;
	}
	
}
