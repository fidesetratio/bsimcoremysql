package com.ekalife.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ekalife.datatables.SimplePaginator;
import com.ekalife.datatables.TablePaginator;
import com.ekalife.datatables.models.PaginationCriteria;
import com.ekalife.datatables.models.TablePage;
import com.ekalife.model.ParamSimpleString;
import com.ekalife.services.BsimServices;
import com.ekalife.services.CrudParamServices;

@RestController
@RequestMapping("/crud")
public class CrudRestParameter {
	
 
	@Autowired
	private BsimServices services;
	
	
	
	private static Logger logger = LoggerFactory.getLogger(CrudRestParameter.class);
	
	@RequestMapping(value="/list",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody TablePage list(@RequestBody PaginationCriteria treq){
		logger.info("hit by several test"+treq);
		TablePaginator paginator = new SimplePaginator(new CrudParamServices(services));
		TablePage tablePage =  paginator.getPage(treq);
		return tablePage;
	}
		
	
	@RequestMapping(value="/viewdetail",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ParamSimpleString viewdetail(@RequestBody ParamSimpleString paramSimpleString){
		
		System.out.println(paramSimpleString.getParamType());
		return paramSimpleString;
	};
}
