package com.cg.oam.Entity;

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
	  @JoinColumn(name="medicine_id")
	  private List<Medicine> medicines;
	  
	  @OneToMany
	  @JoinColumn(name="order_id")
	  private List<OrderDetails> orders;
	  
	  
	  @OneToMany
	  @JoinColumn(name="prescription_id")
	  private List<Prescription> prescriptions;
	  //unidirectional
	  //@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	  //private List<Prescription> prescriptions;

	
}
