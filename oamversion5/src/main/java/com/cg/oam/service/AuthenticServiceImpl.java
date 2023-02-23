package com.cg.oam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.entity.Customer;
import com.cg.oam.exception.AuthenticationFailureException;
import com.cg.oam.exception.NoUserFoundException;
import com.cg.oam.repository.ICustomerRepository;
@Service
public class AuthenticServiceImpl implements AuthenticationService {
	
	@Autowired
	private ICustomerRepository customerRepository;
	@Override
	public CustomerBean login(String username, String password) {
		Optional<Customer> optionalUser = customerRepository.findByUsername(username);
		if(optionalUser.isEmpty())
			throw new NoUserFoundException();
		
		Customer customer = optionalUser.get();
		CustomerBean user = new CustomerBean(customer,true);
		
		if(!password.equals(user.getPassword())) 
			throw new AuthenticationFailureException();
		
		
		return user;
	}

}
