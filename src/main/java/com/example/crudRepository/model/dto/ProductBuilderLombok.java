package com.example.crudRepository.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

//Example for builder design pattern
@Builder
@Data
public class ProductBuilderLombok implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895747119905987422L;
	
	private String name;
	
	private String description;
	
	private String price;

}
