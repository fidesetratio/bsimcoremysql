package com.ekalife.rest;

import java.util.List;

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
import com.ekalife.model.DetailObject;
import com.ekalife.model.ObjectSimpleString;
import com.ekalife.model.Param;
import com.ekalife.services.BsimServices;
import com.ekalife.services.CrudParamServices;
import com.mysql.fabric.xmlrpc.base.Params;

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
	public @ResponseBody DetailObject viewdetail(@RequestBody ObjectSimpleString paramSimpleString){
		DetailObject detailObject = new DetailObject();
		detailObject.setObject_name(paramSimpleString.getObject_name());
		System.out.println(paramSimpleString.getObject_name().trim());
		List<Param> attribute = services.selectParamTypeAndObjectName("setupproduct", paramSimpleString.getObject_name().trim());
		detailObject.setAttribute(attribute);
		System.out.println(detailObject.getObject_name());
		return detailObject;
	};
	
	
	@RequestMapping(value="/viewdetailbyid",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody Param viewdetailbyid(@RequestBody Param param){
		System.out.println("param"+param.getId());
		Param p = services.selectParamById(param.getId());
		return p;
	};
}
