package com.cg.oam.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.Bean.CustomerBean;
import com.cg.oam.Entity.Customer;
import com.cg.oam.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	@Autowired
    private AdminService adminService;
	
	
	//adding customer
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer)
	{
		adminService.createUser(customer);
		return ResponseEntity.ok("Customer Saved");
	}
	//view all customers
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerBean>> viewAllCustomers(){
		List<CustomerBean> allCustomers = adminService.findAllUsers();
		return ResponseEntity.ok(allCustomers);
		
	}
	
    
}
