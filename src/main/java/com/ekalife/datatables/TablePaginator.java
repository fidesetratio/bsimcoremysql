package com.ekalife.datatables;

import com.ekalife.datatables.models.PaginationCriteria;
import com.ekalife.datatables.models.TablePage;


/**
 * 
 * @author Patar.Tambunan
 *
 */
public interface  TablePaginator {
	
	TablePage getPage(PaginationCriteria paginationCriteria);

}
