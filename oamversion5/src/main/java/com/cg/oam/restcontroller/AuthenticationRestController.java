package com.cg.oam.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.bean.CustomerBean;
import com.cg.oam.bean.LoginReq;
import com.cg.oam.service.AuthenticationService;



@RestController
@CrossOrigin(origins="https://localhost:3000")
@RequestMapping("/auth")

public class AuthenticationRestController {
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<CustomerBean> doLogin(@RequestBody LoginReq login){
		CustomerBean customer = authenticationService.login(login.getUsername(), login.getPassword());
		return ResponseEntity.ok(customer);
	}
	
	
	

}
