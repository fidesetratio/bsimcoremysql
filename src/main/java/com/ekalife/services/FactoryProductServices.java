package com.ekalife.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekalife.dao.BsimDao;
import com.ekalife.elions.model.Account_recur;
import com.ekalife.elions.model.AddressBilling;
import com.ekalife.elions.model.Benefeciary;
import com.ekalife.elions.model.Cmdeditbac;
import com.ekalife.elions.model.Datausulan;
import com.ekalife.elions.model.PembayarPremi;
import com.ekalife.elions.model.Pemegang;
import com.ekalife.elions.model.PesertaPlus_mix;
import com.ekalife.elions.model.Rekening_client;
import com.ekalife.elions.model.Tertanggung;
import com.ekalife.model.Param;

@Service
public class FactoryProductServices {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	public Cmdeditbac loadCmdEditBac(String setupProduct) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Cmdeditbac cmdeditbac = new Cmdeditbac();
		HashMap<String,Object> container = new HashMap<String, Object>();
		
		container.put("pemegang", new Pemegang());
		container.put("addressBilling", new AddressBilling());
		container.put("datausulan", new Datausulan());
		container.put("tertanggung", new Tertanggung());
		container.put("pesertaPlusmix", new PesertaPlus_mix());
		container.put("accountRecur", new Account_recur());
		container.put("pembayarPremi", new PembayarPremi());
		container.put("beneficiary", new Benefeciary());
		container.put("rekeningClient", new Rekening_client());
		
		List<Param> listOfParams = selectSetupProject(setupProduct);
		for(Param p :listOfParams){
				String jenis_object = p.getJenis_object();
				String object_name = p.getObject_name();
				String object_var_type = p.getObject_var_type();
				String object_var_def_value = p.getObject_var_def_val();
				if(jenis_object.equals("pemegang")){
					Pemegang pem = (Pemegang) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(pem, p.getObject_name(), val);
				};
				if(jenis_object.equals("addressBilling")){
					AddressBilling addr = (AddressBilling) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(addr, p.getObject_name(), val);
				};
				if(jenis_object.equals("datausulan")){
					Datausulan du = (Datausulan) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(du, p.getObject_name(), val);
				};
				if(jenis_object.equals("tertanggung")){
					Tertanggung ttg = (Tertanggung) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(ttg, p.getObject_name(), val);
				};
				if(jenis_object.equals("pesertaPlusmix")){
					PesertaPlus_mix ppmix = (PesertaPlus_mix) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(ppmix, p.getObject_name(), val);
				};
				if(jenis_object.equals("accountRecur")){
					Account_recur accRecur = (Account_recur) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(accRecur, p.getObject_name(), val);
				};
				
				if(jenis_object.equals("pembayarPremi")){
					PembayarPremi pemBayarPremi = (PembayarPremi) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(pemBayarPremi, p.getObject_name(), val);
				};
				
				if(jenis_object.equals("beneficiary")){
					Benefeciary benef = (Benefeciary) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(benef, p.getObject_name(), val);
				};
				
				if(jenis_object.equals("rekeningClient")){
					Rekening_client rekeningClient = (Rekening_client) container.get(jenis_object);
					Object val = calculateValue(object_var_type, object_var_def_value);
					PropertyUtils.setSimpleProperty(rekeningClient, p.getObject_name(), val);
				};
				
		}
		
		cmdeditbac.setPemegang((Pemegang)container.get("pemegang"));
		cmdeditbac.setAddressbilling((AddressBilling)container.get("addressBilling"));
		cmdeditbac.setDatausulan((Datausulan)container.get("datausulan"));
		cmdeditbac.setTertanggung((Tertanggung)container.get("tertanggung"));
		cmdeditbac.setAccount_recur((Account_recur)container.get("accountRecur"));
		cmdeditbac.setPembayarPremi((PembayarPremi)container.get("pembayarPremi"));
		
		return cmdeditbac;
	}
	
	
	public static Object calculateValue(String type,String value){
		Object object = null;
		if(type.toUpperCase().trim().equals("INT")){
			object = Integer.parseInt(value);
		}
		if(type.toUpperCase().trim().equals("String")){
			object = value;
		}
		return object;
	}
	
	public List<Param> selectSetupProject(String paramType){
		BsimDao dao=sqlSession.getMapper(BsimDao.class);
		HashMap<String,Object> params = new HashMap<>();
		params.put("param_type", paramType);
		return (List<Param>)dao.selectSetupProject(params);
	}
}
