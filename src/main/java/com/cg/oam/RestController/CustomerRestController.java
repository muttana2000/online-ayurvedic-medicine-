package com.cg.oam.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Entity.Address;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Service.AddressService;
import com.cg.oam.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private final CustomerService customerService;

	public CustomerRestController(CustomerService customerBeanService) {
		this.customerService = customerBeanService;
	}
	@GetMapping("/register")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer)
	{
		customerService.createUser(customer);
		return ResponseEntity.ok("Thank you for registering");
	}

	@GetMapping("/customers/{userId}")
	public ResponseEntity<CustomerBean> findById(@PathVariable Integer userId) {
		CustomerBean customer = customerService.findById(userId);
		return ResponseEntity.ok(customer);
	}

	@GetMapping("/customers/{userId}/orders")
	public ResponseEntity<List<OrderDetails>> findOrdersByUserId(@PathVariable Integer userId) {
		List<OrderDetails> orders = customerService.findOrdersByUserId(userId);
		return ResponseEntity.ok(orders);
	}

	@GetMapping("/getAllAddress")
	public ResponseEntity<List<Address>> findAllAddressBeans() {
		List<Address> addressBeans = addressService.findAllAddress();
		return ResponseEntity.ok(addressBeans);
	}

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
