package com.example.crudRepository.model;

import java.io.Serializable;

public class SortInputs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4029046286949695756L;
	
	public SortInputs() {
		super();
	}

	private SortInput[] sort;

	public SortInput[] getSort() {
		return sort;
	}

	public void setSort(SortInput[] sort) {
		this.sort = sort;
	}
	
	
}
