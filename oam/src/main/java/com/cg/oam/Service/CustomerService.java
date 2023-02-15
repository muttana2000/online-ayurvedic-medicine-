package com.cg.oam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Entity.OrderDetails;
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

	@Transactional
	public CustomerBean findById(Integer userId) {
		Optional<Customer> checkcustomer = customerRepository.findById(userId);
	    if (checkcustomer == null) {
	      throw new NoSuchElementException();
	    }
		Customer customer = customerRepository.findByUserId(userId);
		return new CustomerBean(customer);
	}

	@Transactional
	public List<OrderDetails> findOrdersByUserId(Integer userId) {
		Optional<Customer> checkcustomer = customerRepository.findById(userId);
	    if (checkcustomer == null) {
	      throw new NoSuchElementException();
	    }
		Customer customer = customerRepository.findByUserId(userId);
		return customer.getOrders();
	}

	@Transactional
	public Customer createUser(Customer customer) {
		if(customer.getFirstName() == null || customer.getFirstName().length()==0 ||
			    customer.getLastName() == null || customer.getLastName().length()==0 ||
			    customer.getGender() == null || customer.getGender().length()==0 ||
			    customer.getEmail() == null || customer.getEmail().length()==0)
			{
				throw new EmptyInputException();
			}
			if(customer.getAge()==0 || customer.getAge()<0 ||
					customer.getPhoneNumber()==0 || customer.getPhoneNumber()<0) {
				throw new InvalidInputException();
			}
		return customerRepository.save(customer);
	}


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
