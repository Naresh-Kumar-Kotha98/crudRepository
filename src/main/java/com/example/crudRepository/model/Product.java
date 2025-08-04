package com.example.crudRepository.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class Product implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895747119905987422L;

	@Id
	private String id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String description;
}
