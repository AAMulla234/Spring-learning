package com.pmc.product;

import java.io.Serializable;

public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3899861416051868470L;
	private String sku;
	private String name;
	private String description;
	private String group;
	
	public Product(String sku, String name, String description, String group) {
		super();
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.group = group;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	

}
