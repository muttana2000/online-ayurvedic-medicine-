package com.cg.oam.Bean;

import com.cg.oam.Entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryBean{
	private Integer categoryId;
	private String categoryName;
	
	public CategoryBean(Category category) {
	categoryId=category.getCategoryId();
	categoryName=category.getCategoryName();
		
		
	}
}
