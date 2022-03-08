package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Product;
import com.cognixia.jump.service.ProductService;

@RequestMapping("/api")
@RestController
public class ProductController {
	
	@Autowired
	ProductService serv;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		return serv.getAllProducts();
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		
		product.setId(null);
		
		Product created = serv.createProduct(product);
		
		return ResponseEntity.status(201).body(created);
	}
	
}
