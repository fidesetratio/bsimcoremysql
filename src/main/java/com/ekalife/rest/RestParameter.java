package com.ekalife.rest;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ekalife.elions.model.Cmdeditbac;
import com.ekalife.model.Param;
import com.ekalife.model.product.Product;
import com.ekalife.model.product.ProductResult;
import com.ekalife.product.util.ProductParamUtil;
import com.ekalife.request.ProductRequest;
import com.ekalife.services.Bsim;
import com.ekalife.services.FactoryProductServices;


@RestController
@RequestMapping("api")
public class RestParameter {
	@Autowired
	private Bsim bsimServices;
	
	@Autowired
	private FactoryProductServices factoryProductServices;
	
	@RequestMapping(value="/restTesting", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> restTesting( ) throws ParseException{
		ResponseEntity<String> entity = new ResponseEntity<>(Integer.toString(bsimServices.selectParam(100, 120).size()),HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value="/checkingall", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResult> checkingall(@RequestBody ProductRequest request) throws ParseException{
		Product product =  new Product(bsimServices, request);
		ProductResult productResult = product.getResult();
		ResponseEntity<ProductResult> response  = new ResponseEntity<ProductResult>(productResult,HttpStatus.OK);
		return response;
		
	}
		
	

	@RequestMapping(value="/paramTesting", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Param>> paramTesting( ) throws ParseException{
		int lsbs_id = 100;
		int lsdbs_number = 10;		
		ProductParamUtil productParamUtil = new ProductParamUtil(100, 10, bsimServices);		
		ResponseEntity<List<Param>> entity = new ResponseEntity<>(productParamUtil.getParams(),HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value="/setupProduct", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cmdeditbac> setupProduct( ) throws ParseException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Cmdeditbac cmdEditBac = new Cmdeditbac();
		cmdEditBac = factoryProductServices.loadCmdEditBac("setupProduct");
		ResponseEntity<Cmdeditbac> entity = new ResponseEntity<>(cmdEditBac,HttpStatus.OK);
		return entity;
	}
}
