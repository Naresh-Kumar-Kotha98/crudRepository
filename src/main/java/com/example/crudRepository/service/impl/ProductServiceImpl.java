package com.example.crudRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudRepository.CustomException.ResourceNotFoundException;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.ProductResponse;
import com.example.crudRepository.repository.ProductRepository;
import com.example.crudRepository.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResponse saveProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		ProductResponse productResponse = new ProductResponse(savedProduct);
		return productResponse;
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
		Product product = productRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("No product found with id:" + id));
		return product;
	}

	public void deleteProduct(String id) {
//		productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item not found with id: " + id));
		productRepository.deleteById(id);
	}
}
