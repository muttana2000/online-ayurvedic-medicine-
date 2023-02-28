package com.cg.oam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.bean.OrderDetailsBean;
import com.cg.oam.entity.Address;
import com.cg.oam.entity.Customer;
import com.cg.oam.service.AddressService;
import com.cg.oam.service.CustomerService;
import com.cg.oam.service.OrderService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private final CustomerService customerService;
	@Autowired
	private OrderService orderService;

	public CustomerRestController(CustomerService customerBeanService) {
		this.customerService = customerBeanService;
	}

	@PostMapping("/register")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer cust = customerService.addCustomer(customer);
		return ResponseEntity.ok(cust);
	}

	@PostMapping("/{userId}/addAddress")
	public ResponseEntity<Address> addAddressByCustomerId(@RequestBody Address address, Integer userId) {
		Address addrs = customerService.addAddressByCustomerId(address, userId);
		return ResponseEntity.ok(addrs);
	}

	// GET mapping for finding a customer by their userId
	@GetMapping("/{userId}")
	public ResponseEntity<CustomerBean> findById(@PathVariable Integer userId) {
		CustomerBean customer = customerService.findById(userId);
		return ResponseEntity.ok(customer);
	}
	
	
	

	// Put mapping for updating existing customer
	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomerByUserId(@RequestBody Customer customer) {
		Customer customernew = customerService.updateCustomer(customer);
		return ResponseEntity.ok(customernew);

	}

	// find all orders of customer by customerID
	@GetMapping("/orders/{userId}")
	public ResponseEntity<List<OrderDetailsBean>> getOrdersByUserId(@PathVariable Integer userId) {
		List<OrderDetailsBean> orderList = orderService.getOrdersByUserId(userId);
		if (orderList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@PutMapping("/orders/{userId}/{orderId}")
	public ResponseEntity<OrderDetailsBean> cancelOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
		OrderDetailsBean cancelledOrder = orderService.cancelOrder(userId, orderId);
		return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
	}

	// need to write Upload Prescription at the time of front end

	@PostMapping("/addAddress")
	public ResponseEntity<String> addAddress(@RequestBody Address address) {
		addressService.addAddress(address);
		return ResponseEntity.ok("Address Saved");
	}

	@PutMapping("/updateAddress")
	public ResponseEntity<String> updateAddress(@RequestBody Address address) {
		addressService.addAddress(address);
		return ResponseEntity.ok("Address Bean Updated");
	}

	@DeleteMapping("/deleteAddress/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable("addressId") Integer addressId) {
		addressService.deleteAddressById(addressId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
}
