package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<Product> getProductWithHighPrice(double price){
		return repo.productWithHigherPrice(price);
	}
	
	public Product getProductWithId(int id) {
		Optional<Product> found = repo.findById(id);
		return found.get();
	}
	
	public Product updateProduct(Product product) {
		return repo.save(product);
	}
	
	public boolean deleteProduct(int id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
}
