package com.cg.oam.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.Bean.PrescriptionBean;
import com.cg.oam.Entity.Prescription;
import com.cg.oam.Repository.IPrescriptionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrescriptionService {
	//injection of repository
	@Autowired
	private IPrescriptionRepository prescriptionRepository;
	
	
	//to add prescription while ordering medicine
	@Transactional
	public Prescription addPrescription(PrescriptionBean prescriptionBean) {
		if (prescriptionBean.getUploadDate().getYear() < 1 || 
				prescriptionBean.getUploadDate().getMonthValue() < 1 || 
				prescriptionBean.getUploadDate().getMonthValue() > 12 || 
				prescriptionBean.getUploadDate().getDayOfMonth() < 1 ||
				prescriptionBean.getUploadDate().getDayOfMonth() > 31) {
			      throw new IllegalArgumentException("Invalid date");
		}
		if (prescriptionBean.getPrescriptionImage() == null) {
		      throw new IllegalArgumentException("Prescription image cannot be null");
		      }
		Prescription prescription = new Prescription(prescriptionBean);
		return prescriptionRepository.save(prescription);
	}
	//uploadimage
	public Prescription uploadPresciptionImage(Prescription prescription,String path) throws IOException {
		 // Load the image file as binary data
	    File file = new File(path);
	    byte[] fileContent = Files.readAllBytes(file.toPath());
	    // Encode the binary data to a Base64 encoded string
	    //String encodedString = Base64.getEncoder().encodeToString(fileContent);
	    prescription.setPrescriptionImage(fileContent);
	    return prescription;
	}
	
	//show all prescriptions
	public List<Prescription> getAllPrescriptions() {
		return prescriptionRepository.findAll();
	}
	
	//for any chance , method to delete prescription
	public void deletePrescription(Prescription prescription) {
		prescriptionRepository.delete(prescription);
	}

}
