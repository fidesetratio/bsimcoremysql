package com.app.services;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BsimDao;
import com.app.model.Param;
import com.app.model.ProductFunction;

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
