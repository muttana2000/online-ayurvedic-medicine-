package com.cg.oam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.entity.Address;
import com.cg.oam.entity.Customer;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.repository.IAddressRepository;
import com.cg.oam.repository.ICustomerRepository;

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
		//Address address = addressRepository.findByAddressId(customer.getAddress().getAddressId());
		//customer.setAddress(address);
		addressRepository.save(customer.getAddress());
		return customerRepository.save(customer);
	}

	// adding address to customer by customer Id
	@Transactional
	public Address addAddressByCustomerId(Address address, Integer customerId) {
		Customer customer = customerRepository.findByUserId(customerId);
		if (customer == null) {
			throw new NoSuchElementException();
		}
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
		return new CustomerBean(customer,true);
	}

    
	//update customer by customer Id
	@Transactional
	public Customer updateCustomer(Customer customer) {
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
		//Address address = addressRepository.findByAddressId(customer.getAddress().getAddressId());
		//customer.setAddress(address);
		addressRepository.save(customer.getAddress());
		return customerRepository.save(customer);
	}
	
	

	/*
	 * @Transactional public CustomerBean findById(Integer userId) {
	 * Optional<Customer> checkcustomer = customerRepository.findById(userId); if
	 * (checkcustomer == null) { throw new NoSuchElementException(); } Customer
	 * customer = customerRepository.findByUserId(userId); return new
	 * CustomerBean(customer); }
	 *
	 @Transactional public List<OrderDetails> findOrdersByUserId(Integer userId) {
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
