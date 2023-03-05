package com.cg.oam.service;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cg.oam.bean.PrescriptionBean;
import com.cg.oam.entity.Customer;

import com.cg.oam.entity.Prescription;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.repository.ICustomerRepository;
import com.cg.oam.repository.IPrescriptionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PrescriptionService {
	// injection of repository
	@Autowired
	private IPrescriptionRepository prescriptionRepository;

	@Autowired
	private ICustomerRepository customerRepository;

	// to add prescription while ordering medicine
	@Transactional
	public Prescription addPrescription(Prescription prescription) {
		if (prescription.getUploadDate().getYear() < 1 || prescription.getUploadDate().getMonthValue() < 1
				|| prescription.getUploadDate().getMonthValue() > 12 || prescription.getUploadDate().getDayOfMonth() < 1
				|| prescription.getUploadDate().getDayOfMonth() > 31) {
			throw new IllegalArgumentException("Invalid date");
		}
		// if (prescription.getPrescriptionImage() == null) {
		// throw new IllegalArgumentException("Prescription image cannot be null");
		// }
		Optional<Customer> optionalCustomer = customerRepository.findById(prescription.getCustomer().getUserId());
		if (optionalCustomer.isEmpty()) {
			throw new NoSuchElementException();
		}
		prescription.setCustomer(optionalCustomer.get());
		return prescriptionRepository.save(prescription);
	}

	

	// find Prescription by user Id
	@Transactional
	public List<PrescriptionBean> findPrescriptionsByUserId(Integer userId) {
		Optional<Customer> checkCustomer = customerRepository.findById(userId);
		if (!checkCustomer.isPresent()) {
			throw new NoSuchElementException();
		}
		Customer customer = checkCustomer.get();
		List<Prescription> prescriptions = customer.getPrescriptions();
		List<PrescriptionBean> prescriptionBeans = new ArrayList<>();
		for (Prescription prescription : prescriptions) {
			prescriptionBeans.add(new PrescriptionBean(prescription, true, true));
		}
		return prescriptionBeans;
	}

	// upload Prescription
	@Transactional
	public void uploadPrescription(MultipartFile file, Integer prescriptionId) {
		Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
		if(optionalPrescription.isEmpty()) {
			throw new NoSuchElementException();
		}
		String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		Prescription prescription = optionalPrescription.get();
		try {
			prescription.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prescriptionRepository.save(prescription);
		
	}
	

	// show all prescriptions
	public List<Prescription> getAllPrescriptions() {
		return prescriptionRepository.findAll();
	}

	// for any chance , method to delete prescription
	public void deletePrescription(Prescription prescription) {
		prescriptionRepository.delete(prescription);
	}
	
	
	//chrome
	public Prescription save(Prescription prescription) {
		return prescriptionRepository.save(prescription);
	}

}
