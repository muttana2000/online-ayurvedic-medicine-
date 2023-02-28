package com.cg.oam.bean;

import java.time.LocalDate;

import com.cg.oam.entity.Medicine;

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
	private String image;
	private LocalDate expiryDate;
	private Integer stock;
	private Integer rating;
	private DescriptionBean description;
	private CategoryBean category;
	
	
	public MedicineBean(Medicine medicine,Boolean buildDescription , Boolean buildCategory) {
		medicineId=medicine.getMedicineId();
		medicineName=medicine.getMedicineName();
		medicineCost=medicine.getMedicineCost();
		companyName=medicine.getCompanyName();
		manufactureDate=medicine.getManufactureDate();
		expiryDate=medicine.getExpiryDate();
		stock=medicine.getStock();
		rating=medicine.getRating();
		image = medicine.getImage();
		if(buildCategory) {
			
			description = new DescriptionBean(medicine.getDescription(),false);
		}
		if(buildCategory) {
			category = new CategoryBean(medicine.getCategory(),false);
		}
	}


}
