package com.cg.oam.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.Entity.Customer;
import com.cg.oam.Repository.ICustomerRepository;
import com.cg.oam.Service.CustomerService;

@SpringBootTest
public class CustomerTest {
	@Mock
	private ICustomerRepository cust; // creating mock instance for class

	@InjectMocks
	private CustomerService service = new CustomerService(); // creating new instance for Service class and
																		// injecting the mock instance

	@BeforeEach
	public void beforeEach() {

		Customer cust = new Customer();
		cust.setUsername("shiva@3270");
		when(service.createUser(any(Customer.class))).thenReturn(cust);
						// TODO Auto-generated method stub
	}																

	@Test
	@DisplayName(value = "Testing customer for 1111")
	void testAddCustomer1() {
		Customer custDto = new Customer();
		custDto.setUsername("shiva@3270");
		assertTrue(service.createUser(custDto).equals("shiva@3270"));
	}
}