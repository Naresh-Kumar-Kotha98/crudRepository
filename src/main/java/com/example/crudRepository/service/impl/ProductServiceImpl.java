package com.example.crudRepository.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.crudRepository.CustomException.ResourceNotFoundException;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.ProductResponse;
import com.example.crudRepository.repository.ProductRepository;
import com.example.crudRepository.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  
  private final RestTemplate restTemplate;
  
  private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
  
  @Value("${crudRepo.services.mtservice.url:http://localhost:8616/mt/products}")
  private String mtMSUrl;  

//  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, RestTemplate restTemplate) {
    this.productRepository = productRepository;
    this.restTemplate = restTemplate;
  }

  @Caching(evict = { @CacheEvict(cacheNames = "cachedProducts", allEntries = true) })
  public ProductResponse saveProduct(Product product) {
    System.out.println("create product");
    Product savedProduct = productRepository.save(product);
    ProductResponse productResponse = new ProductResponse(savedProduct);
    log.warn("procyt createdwith id:" + product.getId());
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
    
    
    //to get data from mtfeatures(Multi_THreading project) project and to integrate with response
    //url-"http://localhost:8616/mt/products"
    logger.warn("warninf get mtMSulr:" + mtMSUrl);
    
    try {
      String prop = restTemplate.getForObject(mtMSUrl, String.class);
      product.setPropFromOtherMS(prop);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    logger.warn("warninf get product by id:" + product.getId());
    
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
