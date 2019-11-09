package com.app.product.util;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Param;
import com.app.services.Bsim;

public class ProductParamUtil {
	
	private int lsbs_id;
	private int lsdbs_number;
	private Bsim bsimService;
	private String paramType;
	private int carabayar;
	private String kurs;
	
	private List<Param> params;
	
	public ProductParamUtil(int lsbs_id, int lsdbs_number, Bsim bsimServices) {
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.bsimService = bsimServices;
		this.paramType="initproduct";
		this.params = new ArrayList<Param>();
		this.initParam();
	};
	
	public ProductParamUtil(int lsbs_id, int lsdbs_number,int carabayar, String kurs, Bsim bsimServices) {
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.kurs = kurs;
		this.carabayar = carabayar;
		this.bsimService = bsimServices;
		this.paramType="initproduct";
		this.params = new ArrayList<Param>();
		this.initParamNew();
	};
	
	public ProductParamUtil(int lsbs_id, int lsdbs_number, Bsim bsimServices,String paramType) {
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.bsimService = bsimServices;
		this.paramType=paramType;
		this.params = new ArrayList<Param>();
		this.initParam();
	};
	
	
	
	public ProductParamUtil(int lsbs_id, int lsdbs_number, Bsim bsimServices,String paramType,int carabayar, String kurs) {
		this.lsbs_id = lsbs_id;
		this.lsdbs_number = lsdbs_number;
		this.bsimService = bsimServices;
		this.paramType=paramType;
		this.kurs =kurs;
		this.carabayar = carabayar;
		this.params = new ArrayList<Param>();
		this.initParamNew();
	};
	

	private void initParam() {
		this.params = this.bsimService.selectParamByType(lsbs_id, lsdbs_number, paramType);
	}

	
	private void initParamNew() {
		this.params = this.bsimService.selectParamByType(lsbs_id, lsdbs_number, paramType,carabayar,kurs);
	}
	public List<Param> getParams() {
		return params;
	}
	
	
	
	


}
