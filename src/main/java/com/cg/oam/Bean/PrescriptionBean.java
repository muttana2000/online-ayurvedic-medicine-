package com.cg.oam.Bean;

import java.time.LocalDate;
import com.cg.oam.Entity.Prescription;
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

	public PrescriptionBean(Prescription prescription) {
		prescriptionId=prescription.getPrescriptionId();
		uploadDate=prescription.getUploadDate();
		validationDate=prescription.getValidationDate();
		prescriptionImage=prescription.getPrescriptionImage();
				
	}
}
