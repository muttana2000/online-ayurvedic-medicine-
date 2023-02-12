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

import com.cg.oam.Bean.AddressBean;
import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Bean.PrescriptionBean;
import com.cg.oam.Entity.Address;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Entity.OrderDetails;
import com.cg.oam.Service.AddressService;
import com.cg.oam.Service.CustomerService;
import com.cg.oam.Service.OrderService;

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
	public ResponseEntity<Address> addAddressByCustomerId(@RequestBody AddressBean address, Integer userId) {
		Address addrs = customerService.addAddressByCustomerId(address, userId);
		return ResponseEntity.ok(addrs);
	}

	// GET mapping for finding a customer by their userId
	@GetMapping("/{userId}")
	public ResponseEntity<CustomerBean> findById(@PathVariable Integer userId) {
		CustomerBean customer = customerService.findById(userId);
		return ResponseEntity.ok(customer);
	}

	// GET mapping for finding orders of a customer by their userId
	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<OrderDetails>> findOrdersByUserId(@PathVariable Integer userId) {
		List<OrderDetails> orders = customerService.findOrdersByUserId(userId);
		return ResponseEntity.ok(orders);
	}

	// Get mapping for finding all prescriptions by userId
	@GetMapping("/getPrescriptions/{userId}")
	public List<PrescriptionBean> getPrescriptionsByUserId(@PathVariable("userId") Integer userId) {
		return customerService.findPrescriptionsByUserId(userId);
	}

	// Put mapping for updating existing customer
	@PutMapping("/updateCustomer/{userId}")
	public ResponseEntity<Customer> updateCustomerByUserId(@RequestBody CustomerBean customerBean,
			@PathVariable Integer userId) {
		Customer customer = customerService.updateCustomer(customerBean, userId);
		return ResponseEntity.ok(customer);

	}

	// find all orders of customer by customerID
	@GetMapping("/orders/{userId}")
	public ResponseEntity<List<OrderDetails>> getOrdersByUserId(@PathVariable Integer userId) {
		List<OrderDetails> orderList = orderService.getOrdersByUserId(userId);
		if (orderList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@PutMapping("/orders/{userId}/{orderId}")
	public ResponseEntity<OrderDetails> cancelOrder(@PathVariable Integer userId, @PathVariable Integer orderId) {
		OrderDetails cancelledOrder = orderService.cancelOrder(userId, orderId);
		return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
	}

	// need to write Upload Prescription at the time of front end

	// @GetMapping("/customers/{userId}")
	// public ResponseEntity<CustomerBean> findById(@PathVariable Integer userId) {
	// CustomerBean customer = customerService.findById(userId);
	// return ResponseEntity.ok(customer);
	// }

	// @GetMapping("/customers/{userId}/orders")
	// public ResponseEntity<List<OrderDetails>> findOrdersByUserId(@PathVariable
	// Integer userId) {
	// List<OrderDetails> orders = customerService.findOrdersByUserId(userId);
	// return ResponseEntity.ok(orders);
	// }

	// @GetMapping("/getAllAddress")
	// public ResponseEntity<List<Address>> findAllAddressBeans() {
	// List<Address> addressBeans = addressService.findAllAddress();
	// return ResponseEntity.ok(addressBeans);
	// }

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
