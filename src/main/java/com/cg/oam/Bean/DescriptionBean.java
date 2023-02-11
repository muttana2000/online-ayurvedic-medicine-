package com.cg.oam.Bean;

import com.cg.oam.Entity.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionBean {
	private Integer descriptionId;
	private String details;
	private String medicineType;
	private String ingredients;
	private String quantity;
	
	private MedicineBean medicineBean;
	
	public DescriptionBean(Description description, Boolean buildMedicine) {
		descriptionId=description.getDescriptionId();
		details=description.getDetails();
		medicineType=description.getMedicineType();
		ingredients=description.getIngredients();
		quantity=description.getQuantity();
		if(buildMedicine) {
			medicineBean = new MedicineBean(description.getMedicine(),false);
		}
	}

}
