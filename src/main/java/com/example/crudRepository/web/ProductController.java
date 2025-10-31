package com.example.crudRepository.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudRepository.model.Order;
import com.example.crudRepository.model.PageProduct;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.OrderRequest;
import com.example.crudRepository.model.dto.ProductDTO;
import com.example.crudRepository.model.dto.ProductResponse;
import com.example.crudRepository.service.impl.ProductServiceImpl;
import com.example.crudRepository.service.impl.TransactionalServiceImpl;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
@Schema(title = "Prod Controller")
public class ProductController {

	private final ProductServiceImpl productService;
	private final TransactionalServiceImpl transactionalServiceImpl;

	@Autowired
	public ProductController(ProductServiceImpl productService, TransactionalServiceImpl transactionalServiceImpl) {
		this.productService = productService;
		this.transactionalServiceImpl = transactionalServiceImpl;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getProducts();
		return new ResponseEntity<>(products, null, HttpStatus.OK);
	}
	
	@GetMapping("/pageSize")
	public ResponseEntity<PageProduct> getProductsByPageSize(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "1") int size,@RequestParam String sort) {
		
//		Sort sortVal = SortUtil.getSortPattern(sort);
//		Pageable pageable = PageRequest.of(page, size, sortVal);
		Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
		Page<Product> products = productService.getProductsByPageSize(pageable);
		PageProduct pageProduct = new PageProduct();
		pageProduct.setProducts(products.getContent());
		pageProduct.setPage(products.getPageable().getPageNumber());
		pageProduct.setSize(products.getPageable().getPageSize());
		pageProduct.setTotalCount((int)products.getTotalElements());
		return new ResponseEntity<>(pageProduct, null, HttpStatus.OK);
	}
	
	//get by id 
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(String id) {
		Product product = productService.getProduct(id);
		return new ResponseEntity<>(product, null, HttpStatus.OK);
	}

	//create
	@PostMapping("")
	public ResponseEntity<ProductResponse> createProduct( @Valid @RequestBody ProductDTO productDto) {
		Product product = ProductDTO.productDTOtoProduct(productDto);
		ProductResponse productResponse = productService.saveProduct(product);
		
		return new ResponseEntity<>(productResponse, null, HttpStatus.CREATED);
	}

	//update by id
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathParam(value="") String id) {
		Product savedProduct = productService.updateProduct(product, id);
		return new ResponseEntity<>(savedProduct, null, HttpStatus.OK);
	}

	// delete by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathParam(value = "product id") String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product deleted successfully", null, HttpStatus.NO_CONTENT);
	}
	
  // create
  @PostMapping("/transactional")
  public ResponseEntity<Order> transactionalEg(@RequestBody OrderRequest orderRequest) {
    Order orderResponse = transactionalServiceImpl.processOrder(orderRequest);

    return new ResponseEntity<Order>(orderResponse, null, HttpStatus.CREATED);
  }
}
