package com.example.crudRepository.model;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;


@Document
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @Id
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
  private transient Map<String, String> properties;
  
  @Getter
  @Setter
  @Transient
  private String propFromOtherMS;

}
