package com.example.crudRepository.model.dto;

import java.io.Serializable;

import com.example.crudRepository.model.Product;


public class ProductDTO implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895747119905987422L;
	
	private String name;
	
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Product productDTOtoProduct(ProductDTO productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		return product;
	}

}
