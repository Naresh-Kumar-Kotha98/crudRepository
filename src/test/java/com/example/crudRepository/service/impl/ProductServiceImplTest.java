package com.example.crudRepository.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import com.example.crudRepository.model.Product;
import com.example.crudRepository.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProductServiceImplTest {

  private ProductRepository productRepository;
  private RestTemplate restTemplate;
  private ProductServiceImpl productServiceImpl;
  public static JSONObject productJson = null;
  protected static String dataPathProductJson = "src/test/resources/Product.json";
  
  static {
    try {
      File file = new File("src/test/resources/Product.json");
      String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
      productJson  = new JSONObject(json);
   } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  @BeforeEach
  private void setup() {
    productRepository = mock(ProductRepository.class);
    restTemplate = mock(RestTemplate.class);
    productServiceImpl = new ProductServiceImpl(productRepository, restTemplate);
  }

  @Test
  void testGetProduct() throws Exception {
 // Parse JSON using Jackson
    String productId = "123";
    ObjectMapper mapper = new ObjectMapper();
    Product product = mapper.readValue(productJson.getJSONObject("createProduct").toString() , Product.class);
     
 // Use the data in your test
    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

 // Act
    Product result = productServiceImpl.getProduct(productId);
    
 // Assert
    assertNotNull(result);
    assertEquals("product123", result.getName());
    verify(productRepository, times(1)).findById(productId);
  }

}
