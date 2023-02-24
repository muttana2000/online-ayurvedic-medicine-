package com.cg.oam.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.AbstractUserBean;
import com.cg.oam.entity.AbstractUser;
import com.cg.oam.exception.AuthenticationFailureException;
import com.cg.oam.exception.NoUserFoundException;
import com.cg.oam.repository.IUserRepository;

@Service
public class AuthenticServiceImpl implements AuthenticationService {
	
	@Autowired
    private IUserRepository userRepository;

    @Override
    public AbstractUserBean login(String username, String password) {
        Optional<AbstractUser> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
        	throw new NoUserFoundException();
       
        AbstractUser abstractUser = optionalUser.get();
       
        if(!password.equals(abstractUser.getPassword()))
            throw new AuthenticationFailureException();
        
        AbstractUserBean abstractUserBean = new AbstractUserBean(abstractUser);
       
        return abstractUserBean;
    }




}
