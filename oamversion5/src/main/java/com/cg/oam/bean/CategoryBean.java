package com.cg.oam.bean;

import java.util.ArrayList;
import java.util.List;

import com.cg.oam.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryBean{
	private Integer categoryId;
	private String categoryName;
	
	private List<MedicineBean> medicines;
	
	public CategoryBean(Category category,Boolean buildMedicine) {
	categoryId=category.getCategoryId();
	categoryName=category.getCategoryName();
	if(buildMedicine) {
		medicines = new ArrayList<>();
		category.getMedicines().stream().forEach(c->{medicines.add(new MedicineBean(c,true,false));
		});
	}
		
		
	}
}
