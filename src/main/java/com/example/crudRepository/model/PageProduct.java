package com.example.crudRepository.model;

import java.io.Serializable;
import java.util.List;

public class PageProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5893728549835138802L;

	private List<Product> products;
	
	private int page;
	
	private int size;
	
	private int totalCount;

	public List<Product> getProduct() {
		return products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
