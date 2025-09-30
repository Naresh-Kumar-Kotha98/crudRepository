package com.example.crudRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.crudRepository.CustomException.ResourceNotFoundException;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.ProductResponse;
import com.example.crudRepository.repository.ProductRepository;
import com.example.crudRepository.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Caching(evict = { @CacheEvict(cacheNames = "cachedProducts", allEntries = true) })
  public ProductResponse saveProduct(Product product) {
    System.out.println("create product");
    Product savedProduct = productRepository.save(product);
    ProductResponse productResponse = new ProductResponse(savedProduct);
    return productResponse;
  }

  @Caching(evict = { @CacheEvict(cacheNames = "cachedProducts", allEntries = true) })
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

//	@Cacheable(cacheNames = "cachedProductsByPage")
  public Page<Product> getProductsByPageSize(Pageable pageable) {
    System.out.println("searching in db for all products by pagenumber.");
    Page<Product> products = productRepository.findAll(pageable);
    return products;
  }

  @Cacheable(cacheNames = "cachedProduct", key = "#id")
  public Product getProduct(String id) {
    System.out.println("get product with id:" + id);
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No product found with id:" + id));

    // To test builder design pattern

//    com.example.crudRepository.model.dto.ProductBuilder productt = new com.example.crudRepository.model.dto.ProductBuilder.Builder()
//        .setName(product.getName()).setDescription(product.getDescription()).setPrice(null).build();

//    com.example.crudRepository.model.dto.ProductBuilderLombok productt = com.example.crudRepository.model.dto.ProductBuilderLombok
//        .builder().name(product.getName()).description(product.getDescription()).price(null).build();
//
//    System.out.println("productt" + productt.toString());

    return product;
  }

  @Caching(evict = { @CacheEvict(cacheNames = "cachedProducts", allEntries = true),
      @CacheEvict(cacheNames = "cachedProduct", key = "#id") })
  public void deleteProduct(String id) {
    System.out.println("delete product with id:" + id);
//		productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item not found with id: " + id));
    productRepository.deleteById(id);
  }
}
