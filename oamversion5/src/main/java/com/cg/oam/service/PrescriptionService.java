package com.cg.oam.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
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

	// uploadimage
	public Prescription uploadPresciptionImage(Prescription prescription, String path) throws IOException {
		// Load the image file as binary data
		File file = new File(path);
		byte[] fileContent = Files.readAllBytes(file.toPath());
		// Encode the binary data to a Base64 encoded string
		// String encodedString = Base64.getEncoder().encodeToString(fileContent);
		prescription.setPrescriptionImage(fileContent);
		return prescription;
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
	public PrescriptionBean uploadPrescription(MultipartFile file, Integer customerId) {
		Optional<Customer> checkCustomer = customerRepository.findById(customerId);
		if (!checkCustomer.isPresent()) {
			throw new NoSuchElementException();
		}
		Customer customer = checkCustomer.get();
		Prescription prescription = new Prescription();
		try {
			byte[] bytes = file.getBytes();
			prescription.setPrescriptionImage(bytes);
			prescription.setUploadDate(LocalDate.now());
			prescription.setCustomer(customer);
			customer.getPrescriptions().add(prescription);
			customerRepository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		prescriptionRepository.save(prescription);
		PrescriptionBean bean = new PrescriptionBean(prescription, false, false);
		
		return bean;

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
