package com.ekalife.services;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekalife.dao.BsimDao;
import com.ekalife.model.ObjectSimpleString;
import com.ekalife.model.Param;
import com.ekalife.model.ParamSimpleString;
import com.ekalife.model.ProductFunction;

@Service
public class BsimServices implements Bsim{
	@Autowired
	private SqlSession sqlSession;
	
	public List<Param> selectParam(int lsbs_id,int lsdbs_number){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("lsbs_id", lsbs_id);
		params.put("lsdbs_number", lsdbs_number);
		return (List<Param>)dao.selectParam(params);
	}
	
	
	public List<Param> selectParamByType(int lsbs_id,int lsdbs_number,String paramType){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("lsbs_id", lsbs_id);
		params.put("lsdbs_number", lsdbs_number);
		params.put("param_type", paramType);
		return (List<Param>)dao.selectParamByType(params);
	}
	
	public void updateParam(Integer id,String object_var_def_val,String object_var,String object_var_type){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("id", id);
		params.put("object_var_def_val", object_var_def_val);
		params.put("object_var", object_var);
		params.put("object_var_type", object_var_type);
		dao.updateParam(params);
	}
	
	public List<Param> selectParamTypeAndObjectName(String param_type,String object_name){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("param_type", param_type);
		params.put("object_name", object_name);
		return (List<Param>)dao.selectParamTypeAndObjectName(params);
	}
	public Param selectParamById(Integer id){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("id", id);
		return (Param)dao.selectParamById(params);
	}
	
	public List<ParamSimpleString> selectAllParamType(){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		return (List<ParamSimpleString>)dao.selectAllParamType();
		
	}
	
	public List<ObjectSimpleString> selectAllObjectType(){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		return (List<ObjectSimpleString>)dao.selectAllObjectType();
		
	}
	
	public List<ProductFunction> selectProductFunction(int lsbs_id,int lsdbs_number,int carabayar, String kurs){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("lsbs_id", lsbs_id);
		params.put("lsdbs_number", lsdbs_number);
		params.put("carabayar", carabayar);
		params.put("kurs", kurs);
		return (List<ProductFunction>)dao.selectProductFunction(params);
	}
	public List<HashMap<String,Object>> selectListCommon(HashMap<String,Object> params ){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		return (List<HashMap<String,Object>>)dao.selectListCommon(params);
	}
	public HashMap<String,Object> selectCommon(HashMap<String,Object> params ){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		return (HashMap<String,Object>)dao.selectCommon(params);
	}


	@Override
	public List<Param> selectParam(int lsbs_id, int lsdbs_number, int carabayar, String kurs) {
		// TODO Auto-generated method stub
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("lsbs_id", lsbs_id);
		params.put("lsdbs_number", lsdbs_number);
		params.put("carabayar", carabayar);
		params.put("kurs", kurs);		
		return (List<Param>)dao.selectParamNew(params);
	}


	@Override
	public List<Param> selectParamByType(int lsbs_id, int lsdbs_number, String paramType, int carabayar, String kurs) {
		// TODO Auto-generated method stub
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("lsbs_id", lsbs_id);
		params.put("lsdbs_number", lsdbs_number);
		params.put("param_type", paramType);
		params.put("carabayar", carabayar);
		params.put("kurs", kurs);		
		return (List<Param>)dao.selectParamByTypeNew(params);
	}
		
}
