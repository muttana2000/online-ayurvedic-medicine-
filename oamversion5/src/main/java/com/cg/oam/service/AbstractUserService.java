package com.cg.oam.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.oam.bean.AbstractUserBean;
import com.cg.oam.entity.AbstractUser;
import com.cg.oam.exception.NoUserFoundException;
import com.cg.oam.repository.IUserRepository;

import jakarta.transaction.Transactional;

public class AbstractUserService {
	@Autowired
	private IUserRepository iUserRepository;

	//login service
	@Transactional
		public AbstractUserBean login(String username) {
			Optional<AbstractUser> user = iUserRepository.findByUsername(username);
			if (user.isEmpty()) {
				throw new NoUserFoundException();
			}
			AbstractUserBean abstractUserBean = new AbstractUserBean(user.get());
			return abstractUserBean;
		}
}
