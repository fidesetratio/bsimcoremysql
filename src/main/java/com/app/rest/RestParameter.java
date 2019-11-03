package com.app.rest;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Param;
import com.app.product.util.ProductParamUtil;
import com.app.services.BsimServices;


@RestController
@RequestMapping("api")
public class RestParameter {
	@Autowired
	private BsimServices bsimServices;
	
	@RequestMapping(value="/restTesting", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> restTesting( ) throws ParseException{
		ResponseEntity<String> entity = new ResponseEntity<>(Integer.toString(bsimServices.selectParam(100, 120).size()),HttpStatus.OK);
		return entity;
	}

	
	

	@RequestMapping(value="/paramTesting", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Param>> paramTesting( ) throws ParseException{
		int lsbs_id = 100;
		int lsdbs_number = 10;		
		ProductParamUtil productParamUtil = new ProductParamUtil(100, 10, bsimServices);		
		ResponseEntity<List<Param>> entity = new ResponseEntity<>(productParamUtil.getParams(),HttpStatus.OK);
		return entity;
	}
}
