package com.app.model.product;

import java.util.HashMap;
import java.util.List;

import com.app.exception.ParamException;
import com.app.model.Param;
import com.app.model.ProductFunction;
import com.app.product.util.ProductParam;
import com.app.request.ProductRequest;
import com.app.services.Bsim;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class Product {
	
	private Bsim bsimServices;
	
	private GroovyShell shell;
	private Binding binding;
	
	private int lsbs_id;
	private int lsdbs_number;
	private int caraBayar;
	private String kurs;
	private HashMap<String,Object> rets;
	private HashMap<String,Object> request;
	public static final int ERROR = 1;
	public static final int	 SUCCESS = 0;
	
	
	public static final int DEFAULT_PROCESS=1;
	private ProductResult productResult;
	
	public Product(Bsim bsimServices,int lsbs_id, int lsdbs_number, int caraBayar, String kurs,HashMap<String,Object> request) {
		this.initObject(bsimServices, lsbs_id, lsdbs_number, caraBayar, kurs, request);
	}
	
	
	public Product(Bsim bsimServices,ProductRequest request) {
		this.initObject(bsimServices,request.getLsbs_id(),request.getLsdbs_number(),request.getPaymode(),request.getKurs(),request.getParams());
	
	}
	
	private void initObject(Bsim bsimServices,int lsbs_id, int lsdbs_number, int caraBayar, String kurs,HashMap<String,Object> request) {
		this.bsimServices = bsimServices;
		
		this.productResult = new ProductResult();
		this.binding = new Binding();
		this.shell = new GroovyShell(this.binding);
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.caraBayar = caraBayar;
		this.kurs = kurs;
		this.rets = new HashMap<String, Object>();
		this.request = request; 
		this.setInitVariable(lsbs_id, lsdbs_number, request);
		if(productResult.getStatus() != Product.ERROR) {
		this.proses(Product.DEFAULT_PROCESS);
		}
	}
	
	
	private void setInitVariable(int lsbs_id, int lsdbs_number, HashMap<String,Object> request) {
			ProductParam productParam = new ProductParam(lsbs_id, lsdbs_number, bsimServices,caraBayar,kurs);
			List<Param> params = productParam.getParams();
			for(Param param:params) {
				String variableName = (String)param.getVariable_name();
				String variableType = (String)param.getVariable_type();
				String variableValue = (String)param.getVariable_value();
				if(param.getIs_mapping_object() == 0) {
					if(param.getAllow_internal_input()==1) {
						 variableValue = (String) request.get(variableName);
						 if(variableValue == null) {
								productResult.setMessage("variable :"+variableName+" is null");
								productResult.setStatus(Product.ERROR);
								productResult.setFunction("initVariable");
								return;
								
						 }
					}
					
					if(variableType.equals("INT")){
						System.out.println("input :"+variableName);
						this.binding.setVariable(variableName, Integer.parseInt(variableValue));
					}
					if(variableType.equals("STRING")){
						this.binding.setVariable(variableName,variableValue);
					}
					
					if(variableType.equals("DOUBLE")){
						System.out.println("error:"+variableName+":"+variableValue);
						this.binding.setVariable(variableName,Double.parseDouble(variableValue));
					}
					
					if(param.getAllow_as_output()==1) {
						rets.put(variableName, variableValue);
					}
				}
				
				
			}
			
	}
	
	public void proses(int typeProcess) {
		if(typeProcess == Product.DEFAULT_PROCESS){
			List<ProductFunction>	list = this.bsimServices.selectProductFunction(lsbs_id, lsdbs_number, caraBayar, kurs);
			for(ProductFunction productionFunction:list) {
				if(productionFunction.getFunction_type().trim().toUpperCase().equals("SCRIPTS")) {
					if(productionFunction.getFunction_scripts() != null) {
						Object message = shell.evaluate(productionFunction.getFunction_scripts());
						if(message != null) {
							if(message instanceof String) {
								String m = (String)message;
								if(!m.trim().equals(""))
								{
									productResult.setMessage(m);
									productResult.setFunction(productionFunction.getFunction_name());
									productResult.setStatus(Product.ERROR);
									break;
								}
							};
						};
						
					}
				}// please extract all sql
				
				//else if(productionFunction.getFunction_type().trim().toUpperCase().equals("SCRIPTS"))
			}
		}
	}
	public HashMap<String,Object> getRets(){
		HashMap<String,Object> ret = new HashMap<String, Object>();
		for(String key:rets.keySet()){
			Object value = this.binding.getVariable(key);
			ret.put(key, value);
		}
		return ret;
	}
	
	
	
	public ProductResult getResult() {
		productResult.setOutputs(getRets());
		return productResult;
	}
}
