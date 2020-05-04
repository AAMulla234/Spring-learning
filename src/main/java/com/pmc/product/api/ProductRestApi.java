package com.pmc.product.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmc.product.Product;

@RestController
@RequestMapping("/rest/docker")
public class ProductRestApi {

	@GetMapping("/products")
	public List<Product> getProducts(){
		return mockProducts();
		
	}
	
	private List<Product> mockProducts(){
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("D0001", "Docker","Docker First Testing", "Docker"));
		
		return products;
	}
	
}
