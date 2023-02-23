package com.cg.oam.entity;

import java.util.List;

import com.cg.oam.bean.CategoryBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
	
	public Category(CategoryBean categoryBean) {
		categoryId = categoryBean.getCategoryId();
		categoryName = categoryBean.getCategoryName();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	//bidirectional one-to-Many
	@OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
	private List<Medicine> medicines;

}