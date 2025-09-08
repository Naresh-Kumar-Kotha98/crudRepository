package com.example.crudRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.crudRepository.CustomException.ResourceNotFoundException;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.ProductResponse;
import com.example.crudRepository.repository.ProductRepository;
import com.example.crudRepository.service.ProductService;
import com.example.util.SingleTonCls;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Caching(evict = {
			@CacheEvict(cacheNames = "cachedProducts", allEntries = true)
	})
	public ProductResponse saveProduct(Product product) {
		System.out.println("create product");
		SingleTonCls sIngleTonCls = SingleTonCls.getInstance();
		SingleTonCls sIngleTonCls2 = SingleTonCls.getInstance();
		sIngleTonCls.staticMethDisplay();
		sIngleTonCls.normalMethDisplay();
		Product savedProduct = productRepository.save(product);
		ProductResponse productResponse = new ProductResponse(savedProduct);
		return productResponse;
	}
	
	@Caching(evict = {
			@CacheEvict(cacheNames = "cachedProducts", allEntries = true)
	})
	@CachePut(cacheNames = "cachedProduct", key = "#id")
	public Product updateProduct(Product product, String id) {
		System.out.println("updateing in db:" + id);
//		productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item not found with id: " + id));
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	@Cacheable(cacheNames = "cachedProducts")
	public List<Product> getProducts() {
		System.out.println("searching in db for all products.");
		List<Product> products = productRepository.findAll();
		return products;
	}

	@Cacheable(cacheNames = "cachedProduct", key = "#id")
	public Product getProduct(String id) {
		System.out.println("get product with id:"+ id);
		Product product = productRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("No product found with id:" + id));
		return product;
	}

	@Caching(evict = {
			@CacheEvict(cacheNames = "cachedProducts", allEntries = true),
			@CacheEvict(cacheNames = "cachedProduct", key = "#id")
	})
	public void deleteProduct(String id) {
		System.out.println("delete product with id:"+ id);
//		productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item not found with id: " + id));
		productRepository.deleteById(id);
	}
}
