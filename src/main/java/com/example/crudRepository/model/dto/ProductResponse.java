package com.example.crudRepository.model.dto;

import java.util.Map;


import com.example.crudRepository.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

public class ProductResponse {

  @Getter
  @Setter
	private String id;

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
  private Map<String, String> properties;

  @Getter
  @Setter
  @JsonIgnore
  private String propFromOtherMS;
	
	public ProductResponse(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.stock = product.getStock();
		this.properties= product.getProperties();
		this.propFromOtherMS = product.getPropFromOtherMS();
	}
}
