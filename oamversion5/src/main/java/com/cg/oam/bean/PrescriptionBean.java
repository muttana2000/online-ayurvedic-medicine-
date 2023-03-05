package com.cg.oam.bean;

import java.time.LocalDate;

import com.cg.oam.entity.Prescription;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionBean {
	private Integer prescriptionId;
	private LocalDate uploadDate;
	private LocalDate validationDate;
	@Lob
	private String image;
	
	private CustomerBean customerBean;
	


	public PrescriptionBean(Prescription prescription,Boolean buildOrderDetails,Boolean buildCustomer) {
		prescriptionId=prescription.getPrescriptionId();
		uploadDate=prescription.getUploadDate();
		validationDate=prescription.getValidationDate();
		image=prescription.getImage();
		if(buildOrderDetails) {
			
		}
		if(buildCustomer) {
			customerBean = new CustomerBean(prescription.getCustomer(),false);
		}
				
	}
}
