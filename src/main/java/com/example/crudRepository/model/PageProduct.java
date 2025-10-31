package com.example.crudRepository.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PageProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5893728549835138802L;

  @Getter
  @Setter
	private List<Product> products;
	
  @Getter
  @Setter
	private int page;
	
  @Getter
  @Setter
	private int size;
	
  @Getter
  @Setter
	private int totalCount;

}
