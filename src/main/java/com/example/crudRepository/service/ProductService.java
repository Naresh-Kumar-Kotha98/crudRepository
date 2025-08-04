package com.example.crudRepository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.crudRepository.CustomException.ResourceNotFoundException;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product saveProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}
	
	public Product updateProduct(Product product, String id) {
//		productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item not found with id: " + id));
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	public List<Product> getProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

	public Product getProduct(String id) {
		Product product = productRepository.findById(id).orElse(new Product());
		return product;
	}

	public void deleteProduct(String id) {
//		productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item not found with id: " + id));
		productRepository.deleteById(id);
	}
}
