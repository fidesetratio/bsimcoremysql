package com.app.product.util;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Param;
import com.app.services.BsimServices;

public class ProductParamUtil {
	
	private int lsbs_id;
	private int lsdbs_number;
	private BsimServices bsimService;
	private String paramType;
	
	private List<Param> params;
	
	public ProductParamUtil(int lsbs_id, int lsdbs_number, BsimServices bsimServices) {
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.bsimService = bsimServices;
		this.paramType="initproduct";
		this.params = new ArrayList<Param>();
		this.initParam();
	};
	
	public ProductParamUtil(int lsbs_id, int lsdbs_number, BsimServices bsimServices,String paramType) {
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.bsimService = bsimServices;
		this.paramType=paramType;
		this.params = new ArrayList<Param>();
		this.initParam();
	};
	
	
	private void initParam() {
		this.params = this.bsimService.selectParamByType(lsbs_id, lsdbs_number, paramType);
	}

	public List<Param> getParams() {
		return params;
	}
	
	
	
	


}
