package com.cg.oam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.oam.bean.PrescriptionBean;
import com.cg.oam.entity.Prescription;
import com.cg.oam.service.PrescriptionService;

@CrossOrigin(origins="http://localhost:3000")
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

	@PostMapping("/upload/image/{prescriptionId}")
	public ResponseEntity<String> uploadPrescription(@RequestParam("file") MultipartFile file,@PathVariable Integer prescriptionId){
		prescriptionService.uploadPrescription(file, prescriptionId);
		return ResponseEntity.ok("Prescription uploaded successfully");
	}
	// Get mapping for finding all prescriptions by userId
	@GetMapping("/getPrescriptions/{userId}")
	public List<PrescriptionBean> getPrescriptionsByUserId(@PathVariable("userId") Integer userId) {
		return prescriptionService.findPrescriptionsByUserId(userId);
	}
	// need to do more
	// upload and downLoad images at time of front ent
	


}
