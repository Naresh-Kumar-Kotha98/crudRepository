package com.example.crudRepository.web;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.crudRepository.CrudRepositoryApplication;
import com.example.crudRepository.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = CrudRepositoryApplication.class)
@TestPropertySource(locations = {"classpath:application-dev.properties"})
@ActiveProfiles("dev")
class ProductControllerTest {
  
  @Autowired
  private WebApplicationContext context;
  
  private MockMvc mvc;
  
//  @InjectMocks
//  private ProductController productController;

  private static JSONObject productJson;

  private final String baseUrl = "/products";
  
  protected static String dataPathProductJson = "src/test/resources/Product.json";
  
  private String savedProductId = "";
  
  @BeforeEach
  void setUp() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    if (productJson == null) {
        File file = new File(dataPathProductJson);
        String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        productJson = new JSONObject(json);
    }
  }

  @Test
  public void testCreateProduct() throws Exception {
    String url = baseUrl;
    String payload = productJson.get("createProduct").toString();
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(url);
    request.content(payload);
    
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON))
                      .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
    
    String content = result.getResponse().getContentAsString();
    
    assertNotNull(content);
    
    ObjectMapper mapper = new ObjectMapper();
    Product productResponse = mapper.readValue(content, Product.class); 
    savedProductId = productResponse.getId();
    
//    url = baseUrl + "/" +savedProductId;
//    request = MockMvcRequestBuilders.get(url);
//    
//    result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON))
//                      .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
//    
//    content = result.getResponse().getContentAsString();
//    assertNotNull(content);
//    
//    productResponse = mapper.readValue(content, Product.class); 
//    assertEquals("product123", productResponse.getName());
    
  }
  
//  @Test
//  public void testGetProduct() throws Exception {
//    String url = baseUrl + "/" +savedProductId;
//    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url);
//    
//    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON))
//                      .andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
//    
//    String content = result.getResponse().getContentAsString();
//    assertNotNull(content);
//    
//    ObjectMapper mapper = new ObjectMapper();
//    ProductResponse productResponse = mapper.readValue(content, ProductResponse.class); 
//    assertEquals("product123", productResponse.getName());
//    
//  }

}
