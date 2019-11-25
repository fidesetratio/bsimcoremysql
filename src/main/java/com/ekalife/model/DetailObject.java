package com.ekalife.model;

import java.util.List;

public class DetailObject {
	
	private String object_name;
	
	private List<Param> attribute;
	

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public List<Param> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<Param> attribute) {
		this.attribute = attribute;
	}

}
