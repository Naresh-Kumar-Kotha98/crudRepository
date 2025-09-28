package com.example.crudRepository.model;

import java.io.Serializable;

public class SortInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -812114135622510922L;

	private String field;
	
	private SortDirection direction;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public SortDirection getDirection() {
		return direction;
	}

	public void setDirection(SortDirection direction) {
		this.direction = direction;
	}
	
	public SortInput() {
		super();
	}

	public SortInput(String field, SortDirection direction) {
		super();
		this.field = field;
		this.direction = direction;
	}
	
}
