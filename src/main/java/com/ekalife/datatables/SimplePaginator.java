package com.ekalife.datatables;

import com.ekalife.datatables.data.TableDataException;
import com.ekalife.datatables.data.TableDataService;
import com.ekalife.datatables.models.PaginationCriteria;
import com.ekalife.datatables.models.TablePage;

public class SimplePaginator implements TablePaginator {
	private TableDataService dataService;
	
	
	 public SimplePaginator(TableDataService dataService) {
	        this.dataService = dataService;
	        
	}
	 
	 
	 @Override
		public TablePage getPage(PaginationCriteria paginationCriteria) {
			   TablePage page = new TablePage();
		        try {
		            page = generatePage(paginationCriteria);
		        } catch (TableDataException tde) {
		             page.setError("Error generating table page.");
		        }
		        return page;	
	}
		protected TablePage generatePage(PaginationCriteria paginationCriteria) throws TableDataException {
	        TablePage page = new TablePage();
	        page.setDraw(paginationCriteria.getDraw());
	        page.setRecordsTotal(dataService.countTotalEntries());
	        page.setRecordsFiltered(dataService.countFilteredEntries(paginationCriteria));
	        page.setData(dataService.getPageEntries(paginationCriteria));
	        return page;
		};    	

}
