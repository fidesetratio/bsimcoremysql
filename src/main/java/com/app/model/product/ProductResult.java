package com.app.model.product;

import java.util.HashMap;

public class ProductResult {
	
	private String message;
	private int status;
	private String function;
	
	private HashMap<String,Object> outputs;

	public ProductResult() {
		message = "";
		status = 0;
		outputs = new HashMap<String, Object>();
		function = "";
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public HashMap<String, Object> getOutputs() {
		return outputs;
	}

	public void setOutputs(HashMap<String, Object> outputs) {
		this.outputs = outputs;
	}
	
	
	

}
