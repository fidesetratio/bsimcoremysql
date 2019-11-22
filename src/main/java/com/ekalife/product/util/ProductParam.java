package com.ekalife.product.util;

import com.ekalife.services.Bsim;

public class ProductParam extends ProductParamUtil {

	public ProductParam(int lsbs_id, int lsdbs_number, Bsim bsimServices) {
		super(lsbs_id, lsdbs_number, bsimServices,"initproduct");
	}
	
	public ProductParam(int lsbs_id, int lsdbs_number, Bsim bsimServices,int carabayar,String kurs) {
		super(lsbs_id, lsdbs_number, bsimServices,"initproduct",carabayar,kurs);
	}

}
