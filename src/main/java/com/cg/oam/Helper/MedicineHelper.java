package com.cg.oam.Helper;

import com.cg.oam.Bean.CategoryBean;
import com.cg.oam.Bean.DescriptionBean;
import com.cg.oam.Bean.MedicineBean;
import com.cg.oam.Entity.Medicine;

public class MedicineHelper {
	public static MedicineBean generateMedicineBean(Medicine med) {
		MedicineBean medicineBean=null;
		if(med!=null)
			medicineBean = new MedicineBean(med);
		if(med.getMedicineCategory()!=null)
			medicineBean.setCategory(new CategoryBean(med.getMedicineCategory()));;
		if(med.getDescription()!=null) {
			medicineBean.setDescription(new DescriptionBean(med.getDescription()));
		}
		return medicineBean;
	}
}
