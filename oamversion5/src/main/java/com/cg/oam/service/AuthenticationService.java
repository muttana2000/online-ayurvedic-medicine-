package com.cg.oam.service;

import com.cg.oam.bean.AbstractUserBean;

public interface AuthenticationService {
	
	public AbstractUserBean login(String username,String password);

}
