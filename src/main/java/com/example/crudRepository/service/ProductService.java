package com.example.crudRepository.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.ProductResponse;

@Service
public interface ProductService {

	public ProductResponse saveProduct(Product product);
	
	public Product updateProduct(Product product, String id);

	public List<Product> getProducts();

	public Product getProduct(String id);

	public void deleteProduct(String id);
}
