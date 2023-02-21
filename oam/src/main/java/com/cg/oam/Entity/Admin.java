package com.cg.oam.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("admin")
@EqualsAndHashCode(callSuper=false)
public class Admin extends AbstractUser{
	
	  
	  //unidirectional
	  @OneToMany
	  @JoinColumn(name="fk_medicine_id")
	  private List<Medicine> medicines;
	  
	  //unidirectional
	  @OneToMany
	  @JoinColumn(name="fk_order_id")
	  private List<OrderDetails> orders;
	  
	  
	  //unidirectional
	  @OneToMany
	  @JoinColumn(name="fk_prescription_id")
	  private List<Prescription> prescriptions;
	  //unidirectional
	  //@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	  //private List<Prescription> prescriptions;

	
}
