package com.cg.oam.Helper;

import com.cg.oam.Bean.CategoryBean;
import com.cg.oam.Bean.DescriptionBean;
import com.cg.oam.Bean.MedicineBean;
import com.cg.oam.Entity.Medicine;

public class MedicineHelper {
	
	  public static MedicineBean generateMedicineBean(Medicine med) {
		  MedicineBean medicineBean=null; 
		  if(med!=null)
			  medicineBean = new MedicineBean(med,false);
	  if(med.getMedicineCategory()!=null)
		  medicineBean.setCategoryBean(new CategoryBean(med.getMedicineCategory(),false)); 
		  if(med.getDescription()!=null) {
	  medicineBean.setDescriptionBean(new DescriptionBean(med.getDescription(),false)); }
	  return medicineBean; }
	
}
