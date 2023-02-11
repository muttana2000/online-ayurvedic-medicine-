package com.cg.oam.Entity;

import java.util.List;

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
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	//bidirectional one-to-Many
	@OneToMany(mappedBy = "medicineCategory" , cascade = CascadeType.ALL)
	private List<Medicine> medicines;

}