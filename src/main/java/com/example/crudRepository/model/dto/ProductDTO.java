package com.example.crudRepository.model.dto;

import java.io.Serializable;
import java.util.Map;

import com.example.crudRepository.model.Product;

import lombok.Getter;
import lombok.Setter;


public class ProductDTO implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895747119905987422L;
	
	@Getter
	@Setter
	private String name;
	
  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private int stock;
  
  @Getter
  @Setter
  private transient Map<String, String> properties;
  
//  @Getter
//  @Setter
//  private String propFromOtherMS;
	
	public static Product productDTOtoProduct(ProductDTO productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setStock(productDto.getStock());
		product.setProperties(productDto.getProperties());
//		product.setPropFromOtherMS(productDto.getPropFromOtherMS());
		return product;
	}

}
