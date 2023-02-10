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
	private DescriptionBean description;
	private CategoryBean category;
	//private OrderDetailsBean order;
	
	public MedicineBean(Medicine medicine) {
		medicineId=medicine.getMedicineId();
		medicineName=medicine.getMedicineName();
		medicineCost=medicine.getMedicineCost();
		companyName=medicine.getCompanyName();
		manufactureDate=medicine.getManufactureDate();
		expiryDate=medicine.getExpiryDate();
		stock=medicine.getStock();
		rating=medicine.getRating();
	}


}
