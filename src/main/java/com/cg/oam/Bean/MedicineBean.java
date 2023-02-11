package com.cg.oam.Bean;

import java.time.LocalDate;


import com.cg.oam.Entity.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineBean {
	private Integer medicineId;
	private String medicineName;
	private float medicineCost;
	private String companyName;
	private LocalDate manufactureDate;
	private LocalDate expiryDate;
	private Integer stock;
	private Integer rating;
	private DescriptionBean descriptionBean;
	private CategoryBean categoryBean;
	
	
	public MedicineBean(Medicine medicine,Boolean buildCategoryDescription) {
		medicineId=medicine.getMedicineId();
		medicineName=medicine.getMedicineName();
		medicineCost=medicine.getMedicineCost();
		companyName=medicine.getCompanyName();
		manufactureDate=medicine.getManufactureDate();
		expiryDate=medicine.getExpiryDate();
		stock=medicine.getStock();
		rating=medicine.getRating();
		if(buildCategoryDescription) {
			categoryBean = new CategoryBean(medicine.getMedicineCategory(),false);
			descriptionBean = new DescriptionBean(medicine.getDescription(),false);
		}
	}


}
