package com.ekalife.dao;

import java.util.HashMap;
import java.util.List;

import com.ekalife.model.Param;
import com.ekalife.model.ProductFunction;

public interface BsimDao {
	  List<Param> selectParam(HashMap<String, Object> params);
	  List<Param> selectParamNew(HashMap<String, Object> params);
	  List<Param> selectParamByType(HashMap<String, Object> params);
	  List<Param> selectParamByTypeNew(HashMap<String, Object> params);
	  List<ProductFunction> selectProductFunction(HashMap<String, Object> params);
	  List<HashMap<String,Object>> selectListCommon(HashMap<String, Object> params);
	  HashMap<String,Object> selectCommon(HashMap<String, Object> params);

}
