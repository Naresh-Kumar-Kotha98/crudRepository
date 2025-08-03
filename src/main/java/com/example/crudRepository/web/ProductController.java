package com.example.crudRepository.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
public class ProductController {

	@GetMapping("")
	public ResponseEntity<String> getProducts() {
		return new ResponseEntity<String>("get products", null, 200);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getProductById(@PathParam(value = "product id") String id) {
		return new ResponseEntity<String>("get product - " + id, null, 200);
	}

	@PostMapping("")
	public ResponseEntity<String> createProduct() {
		return new ResponseEntity<String>("create product", null, 200);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateProductById(@PathParam(value = "product id") String id) {
		return new ResponseEntity<String>("update product - " + id, null, 200);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathParam(value = "product id") String id) {
		return new ResponseEntity<String>("delete product - " + id, null, 200);
	}
}
