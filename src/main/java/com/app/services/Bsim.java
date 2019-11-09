package com.app.services;

import java.util.HashMap;
import java.util.List;

import com.app.model.Param;
import com.app.model.ProductFunction;

public interface Bsim {
	
	public List<Param> selectParam(int lsbs_id,int lsdbs_number);
	public List<Param> selectParam(int lsbs_id,int lsdbs_number,int carabayar, String kurs);
	public List<Param> selectParamByType(int lsbs_id,int lsdbs_number,String paramType);
	public List<Param> selectParamByType(int lsbs_id,int lsdbs_number,String paramType,int carabayar, String kurs);
	public List<ProductFunction> selectProductFunction(int lsbs_id,int lsdbs_number,int carabayar, String kurs);
	public List<HashMap<String,Object>> selectListCommon(HashMap<String,Object> params );
	public  HashMap<String,Object> selectCommon(HashMap<String,Object> params);
	

}
