package com.cg.oam.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cg.oam.Bean.AddressBean;
import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Bean.PrescriptionBean;
import com.cg.oam.Entity.Address;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Entity.Prescription;
import com.cg.oam.Repository.IAddressRepository;
import com.cg.oam.Repository.ICustomerRepository;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAddressRepository addressRepository;

	// customer Registration
	@Transactional
	public Customer addCustomer(Customer customer) {
		// Check if all the required fields are filled
		if (customer.getFirstName().isEmpty() || customer.getLastName().isEmpty()
				|| customer.getGender().isEmpty()
				|| customer.getEmail().isEmpty()) {
			// if any of the required fields are empty
			throw new EmptyInputException();
		}
		// Check if the age and phone number are valid
		if (customer.getAge() < 0 || customer.getPhoneNumber() < 0) {
			throw new InvalidInputException();
		}
		Address address = addressRepository.findByAddressId(customer.getAddress().getAddressId());
		customer.setAddress(address);
		return customerRepository.save(customer);
	}

	// adding address to customer by customer Id
	@Transactional
	public Address addAddressByCustomerId(AddressBean addressBean, Integer customerId) {
		Customer customer = customerRepository.findByUserId(customerId);
		if (customer == null) {
			throw new NoSuchElementException();
		}
		Address address = new Address(addressBean);
		address.setCustomer(customer);

		return addressRepository.save(address);

	}

	// find customer by customer Id
	@Transactional
	public CustomerBean findById(Integer userId) {
		// Check if the customer exists
		Optional<Customer> checkcustomer = customerRepository.findById(userId);
		if (checkcustomer == null) {
			// If the customer doesn't exist
			throw new NoSuchElementException();
		}
		// Find the customer by ID
		Customer customer = customerRepository.findByUserId(userId);
		return new CustomerBean(customer, false);
	}

	// Find orders of a customer by user ID
	@Transactional
	public List<OrderDetails> findOrdersByUserId(Integer userId) {
		// Check if the customer exists
		Optional<Customer> checkcustomer = customerRepository.findById(userId);
		if (checkcustomer.isEmpty()) {
			// if the customer doesn't exist
			throw new NoSuchElementException();
		}
		// Find the customer by ID
		Customer customer = customerRepository.findByUserId(userId);
		return customer.getOrders();
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
			prescriptionBeans.add(new PrescriptionBean(prescription,true,true));
		}
		return prescriptionBeans;
	}
	//update customer by customer Id
	@Transactional
	public Customer updateCustomer(CustomerBean customerBean, Integer userId) {
		Optional<Customer> checkcustomer = customerRepository.findById(userId);
		if (checkcustomer.isEmpty()) {
			throw new NoSuchElementException();
		}
		Customer customer = customerRepository.findByUserId(userId);
		customer.setFirstName(customerBean.getFirstName());
		customer.setLastName(customerBean.getLastName());
		customer.setAge(customerBean.getAge());
		customer.setEmail(customerBean.getEmail());
		customer.setGender(customerBean.getGender());
		customer.setPhoneNumber(customerBean.getPhoneNumber());
		//Address address = addressRepository.findByAddressId(customer.getAddress().getAddressId());
		//customer.setAddress(address);
		customerRepository.save(customer);
		return customer;
	}
	//upload Prescription
	@Transactional
	public void uploadPrescription(MultipartFile file, Integer customerId) {
		Optional<Customer> checkCustomer = customerRepository.findById(customerId);
		if (!checkCustomer.isPresent()) {
			throw new NoSuchElementException();
		}
		Customer customer = checkCustomer.get();
		try {
			byte[] bytes = file.getBytes();
			Prescription prescription = new Prescription();
			prescription.setPrescriptionImage(bytes);
			prescription.setUploadDate(LocalDate.now());
			prescription.setCustomer(customer);
			customer.getPrescriptions().add(prescription);
			customerRepository.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @Transactional public CustomerBean findById(Integer userId) {
	 * Optional<Customer> checkcustomer = customerRepository.findById(userId); if
	 * (checkcustomer == null) { throw new NoSuchElementException(); } Customer
	 * customer = customerRepository.findByUserId(userId); return new
	 * CustomerBean(customer); }
	 * 
	 * @Transactional public List<OrderDetails> findOrdersByUserId(Integer userId) {
	 * Optional<Customer> checkcustomer = customerRepository.findById(userId); if
	 * (checkcustomer == null) { throw new NoSuchElementException(); } Customer
	 * customer = customerRepository.findByUserId(userId); return
	 * customer.getOrders(); }
	 * 
	 * @Transactional public Customer createUser(Customer customer) {
	 * if(customer.getFirstName() == null || customer.getFirstName().length()==0 ||
	 * customer.getLastName() == null || customer.getLastName().length()==0 ||
	 * customer.getGender() == null || customer.getGender().length()==0 ||
	 * customer.getEmail() == null || customer.getEmail().length()==0) { throw new
	 * EmptyInputException(); } if(customer.getAge()==0 || customer.getAge()<0 ||
	 * customer.getPhoneNumber()==0 || customer.getPhoneNumber()<0) { throw new
	 * InvalidInputException(); } return customerRepository.save(customer); }
	 */

	/*
	 * @Transactional public CustomerBean updateCustomer(CustomerBean customerBean)
	 * { Customer currentCustomer = customerRepository.findByUserId(userId);
	 * currentCustomer.setFirstName(customerBean.getFirstName());
	 * currentCustomer.setLastName(customerBean.getLastName());
	 * currentCustomer.setEmail(customerBean.getEmail());
	 * currentCustomer.setPhoneNumber(customerBean.getPhoneNumber()); Customer
	 * updatedCustomer = customerRepository.save(currentCustomer); return new
	 * CustomerBean(updatedCustomer); }
	 */
}
