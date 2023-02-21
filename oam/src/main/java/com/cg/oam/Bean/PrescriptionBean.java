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
	private byte[] prescriptionImage;
	
	private CustomerBean customerBean;
	


	public PrescriptionBean(Prescription prescription,Boolean buildOrderDetails,Boolean buildCustomer) {
		prescriptionId=prescription.getPrescriptionId();
		uploadDate=prescription.getUploadDate();
		validationDate=prescription.getValidationDate();
		prescriptionImage=prescription.getPrescriptionImage();
		if(buildOrderDetails) {
			
		}
		if(buildCustomer) {
			customerBean = new CustomerBean(prescription.getCustomer(),false);
		}
				
	}
}
