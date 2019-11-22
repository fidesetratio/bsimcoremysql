package com.ekalife.services;

import java.util.ArrayList;
import java.util.List;

import com.ekalife.datatables.data.DataServiceBase;
import com.ekalife.datatables.data.TableDataException;
import com.ekalife.datatables.models.PaginationCriteria;
import com.ekalife.model.ParamSimpleString;

public class CrudParamServices extends DataServiceBase<ParamSimpleString>{
	
	private BsimServices services;
	private Integer total;
	
	public CrudParamServices(BsimServices services){
	  
	    total = services.selectAllParamType().size();	   
	    this.services = services;
		
	
	   
   }
	

	@Override
	public long countTotalEntries() throws TableDataException {
		// TODO Auto-generated method stub
		return total;
	}

	@Override
	public long countFilteredEntries(PaginationCriteria paginationCriteria)
			throws TableDataException {
		// TODO Auto-generated method stub
		return total;
	}

	@Override
	protected List<ParamSimpleString> getData(PaginationCriteria paginationCriteria)
			throws TableDataException {
		List<ParamSimpleString> list = new ArrayList<ParamSimpleString>();
		/*// TODO Auto-generated method stub
		int start = paginationCriteria.getStart();
		int end = start+paginationCriteria.getLength();
		String search = paginationCriteria.getSearch().getValue();
		search = search.toLowerCase();
		logger.info("mulaiiiiiiiiiiiiiiiiii4..."+total);*/
		list = services.selectAllParamType();
		return list;
	}

}
