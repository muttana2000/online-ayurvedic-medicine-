package com.cg.oam.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.Bean.PrescriptionBean;
import com.cg.oam.Entity.Prescription;
import com.cg.oam.Service.PrescriptionService;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionRestController {
	@Autowired
	private PrescriptionService prescriptionService;
	
	@PostMapping("/add")
	public Prescription addPrescription(@RequestBody PrescriptionBean prescription) {
		return prescriptionService.addPrescription(prescription);
	}
}
