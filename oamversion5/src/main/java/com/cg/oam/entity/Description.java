package com.cg.oam.entity;

import com.cg.oam.bean.DescriptionBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Description {
	public Description(DescriptionBean descriptionBean) {
		descriptionId=descriptionBean.getDescriptionId();
		details=descriptionBean.getDetails();
		medicineType=descriptionBean.getMedicineType();
		ingredients=descriptionBean.getIngredients();
		quantity=descriptionBean.getQuantity();
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "description_id", nullable = false)
	private Integer descriptionId; //primary and not null
	
	@Column(name = "datails")
	private String details;
	@Column(name = "medicine_type")
	private String medicineType;
	@Column(name = "ingredients")
	private String ingredients;
	@Column(name = "quantity")
	private String quantity;
	
	@OneToOne(mappedBy = "description")
	private Medicine medicine;

}