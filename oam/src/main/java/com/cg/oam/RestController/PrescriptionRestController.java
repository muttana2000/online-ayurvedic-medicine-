package com.cg.oam.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.oam.bean.PrescriptionBean;
import com.cg.oam.entity.Prescription;
import com.cg.oam.service.PrescriptionService;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionRestController {
	@Autowired
	private PrescriptionService prescriptionService;

	@PostMapping("/add")
	public ResponseEntity<PrescriptionBean> addPrescription(@RequestBody Prescription prescription) {
		Prescription prescription2 = prescriptionService.addPrescription(prescription);
		PrescriptionBean bean = new PrescriptionBean(prescription2, false, false);
		return ResponseEntity.ok(bean);

	}

	@PutMapping("/upload/{userId}")
	public ResponseEntity<PrescriptionBean> uploadPrescription(@RequestParam("file") MultipartFile file,@PathVariable Integer userId ){
		PrescriptionBean bean = prescriptionService.uploadPrescription(file, userId);
		return ResponseEntity.ok(bean);
	}
	// Get mapping for finding all prescriptions by userId
	@GetMapping("/getPrescriptions/{userId}")
	public List<PrescriptionBean> getPrescriptionsByUserId(@PathVariable("userId") Integer userId) {
		return prescriptionService.findPrescriptionsByUserId(userId);
	}
	// need to do more
	// upload and downLoad images at time of front ent
	@PostMapping("/uploadprescriptions")
	public ResponseEntity<Prescription> uploadPrescription(
			@RequestBody PrescriptionBean prescriptionBean,
			@RequestPart("file") MultipartFile file) {
		
		try {
			Prescription prescription = new Prescription(prescriptionBean);
			prescription.setUploadDate(LocalDate.now());
			prescription.setPrescriptionImage(file.getBytes());
			Prescription savedPrescription = prescriptionService.save(prescription);
			return new ResponseEntity<>(savedPrescription, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
