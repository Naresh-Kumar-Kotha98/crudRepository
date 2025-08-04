package com.example.crudRepository.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudRepository.model.Product;
import com.example.crudRepository.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getProducts();
		return new ResponseEntity<>(products, null, HttpStatus.OK);
	}

	//get by id
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathParam(value = "product id") String id) {
		Product product = productService.getProduct(id);
		return new ResponseEntity<>(product, null, HttpStatus.OK);
	}

	//create
	@PostMapping("")
	public ResponseEntity<Product> createProduct(@RequestBody Product  product) {
		Product savedProduct = productService.saveProduct(product);
		return new ResponseEntity<>(savedProduct, null, HttpStatus.CREATED);
	}

	//update by id
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product  product, @PathParam(value="") String id) {
		Product savedProduct = productService.updateProduct(product, id);
		return new ResponseEntity<>(savedProduct, null, HttpStatus.OK);
	}

	//delete by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathParam(value = "product id") String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product deleted successfully", null, HttpStatus.NO_CONTENT);
	}
}
