package com.cg.oam.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Bean.OrderDetailsBean;
import com.cg.oam.Entity.Customer;

import com.cg.oam.Repository.ICustomerRepository;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.EmptyResultDataAccessException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminService {
	
	//customer
	
	@Autowired
	private ICustomerRepository customerRepository;

	// method to get customer by its id
	public CustomerBean findCustomerById(Integer id) {
		Optional<Customer> checkcustomer = customerRepository.findById(id);
	    if (checkcustomer == null) {
	      throw new NoSuchElementException();
	    }
		Customer customer = customerRepository.findById(id).get();
		return new CustomerBean(customer);
	}

	// method to find all users means all customers also
	public List<CustomerBean> findAllUsers() {
		List<CustomerBean> cusList = new ArrayList<>();
		
		customerRepository.findAll().forEach(cus -> cusList.add(new CustomerBean(cus)));
		if(cusList.isEmpty()) {
			throw new EmptyResultDataAccessException();
		}
		return cusList;
	}

	//method to find all orders done by a customer
	public List<OrderDetailsBean> findOrdersByUserId(Integer userId) {
		Optional<Customer> checkcustomer = customerRepository.findById(userId);
	    if (checkcustomer.isEmpty()) {
	      throw new NoSuchElementException();
	    }
        Customer customer = customerRepository.findByUserId(userId);
        List<OrderDetailsBean> orderList = new ArrayList<>();
		customer.getOrders().forEach(order -> orderList.add(new OrderDetailsBean(order)));
		return orderList;
    }
	
	
	
	/*
	 * // method to creating customer public Customer createUser(Customer customer)
	 * { if(customer.getFirstName() == null || customer.getFirstName().length()==0
	 * || customer.getLastName() == null || customer.getLastName().length()==0 ||
	 * customer.getGender() == null || customer.getGender().length()==0 ||
	 * customer.getEmail() == null || customer.getEmail().length()==0) { throw new
	 * EmptyInputException(); } if(customer.getAge()==0 || customer.getAge()<0 ||
	 * customer.getPhoneNumber()==0 || customer.getPhoneNumber()<0) { throw new
	 * InvalidInputException(); } return customerRepository.save(customer); }
	 */
	/*
	 * // Method to update user, can change user details public Customer
	 * updateUser(Customer customer) { return customerRepository.save(customer); }
	 */
	
	

	
}
