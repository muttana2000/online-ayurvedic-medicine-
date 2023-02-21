package com.cg.oam.helper;

import com.cg.oam.bean.CategoryBean;
import com.cg.oam.bean.DescriptionBean;
import com.cg.oam.bean.MedicineBean;
import com.cg.oam.entity.Medicine;

public class MedicineHelper {
	
	  public static MedicineBean generateMedicineBean(Medicine med) {
		  MedicineBean medicineBean=null; 
		  if(med!=null)
			  medicineBean = new MedicineBean(med,false);
	  if(med.getMedicineCategory()!=null)
		  medicineBean.setCategory(new CategoryBean(med.getMedicineCategory(),false)); 
		  if(med.getDescription()!=null) {
	  medicineBean.setDescription(new DescriptionBean(med.getDescription(),false)); }
	  return medicineBean; }
	
}
