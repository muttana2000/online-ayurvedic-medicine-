package com.cg.oam.service;



import com.cg.oam.bean.CustomerBean;

public interface AuthenticationService {
	
	public CustomerBean login(String username,String password);

}
