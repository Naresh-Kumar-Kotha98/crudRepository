package com.example.crudRepository.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SortInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -812114135622510922L;

  @Getter
  @Setter
	private String field;
	
  @Getter
  @Setter
	private SortDirection direction;

	
	public SortInput() {
		super();
	}

	public SortInput(String field, SortDirection direction) {
		super();
		this.field = field;
		this.direction = direction;
	}
	
}
