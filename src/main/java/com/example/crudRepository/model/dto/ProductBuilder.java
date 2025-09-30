package com.example.crudRepository.model.dto;

import java.io.Serializable;

//Example for builder design pattern
public class ProductBuilder implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2895747119905987422L;
	
	private String name;
	
	private String description;
	
	private String price;
	
  private ProductBuilder(Builder builder) {
    this.name = builder.name;
    this.description = builder.description;
    this.price = builder.price;
  }
	
	public static class Builder{
	  private String name;
	  private String description;
	  private String price;
	  
	  public Builder setName(String name) {
	    this.name = name;
	    return this;
	  }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }
    
    public Builder setPrice(String price) {
      this.price = price;
      return this;
    }
	  
    public ProductBuilder build() {
      return new ProductBuilder(this);
    }

	}

}
