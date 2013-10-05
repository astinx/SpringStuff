package com.generic.server.model;

import java.io.Serializable;

public class Widget implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;

	public Widget(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Widget() {
		super();
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

}
