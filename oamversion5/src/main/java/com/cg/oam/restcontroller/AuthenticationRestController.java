package com.cg.oam.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.bean.AbstractUserBean;
import com.cg.oam.bean.LoginReq;
import com.cg.oam.service.AuthenticationService;



@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/auth")

public class AuthenticationRestController {
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/login")
    public ResponseEntity<AbstractUserBean> doLogin(@RequestBody LoginReq login){
        AbstractUserBean abstractUserBean = authenticationService.login(login.getUsername(), login.getPassword());
        return ResponseEntity.ok(abstractUserBean);
    }

	
	
	

}
