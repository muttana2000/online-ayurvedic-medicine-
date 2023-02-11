package com.cg.oam.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.oam.Entity.AbstractUser;
import com.cg.oam.Repository.IUserRepository;

import jakarta.transaction.Transactional;

public class AbstractUserService {
	@Autowired
	private IUserRepository iUserRepository;

	//login service
	@Transactional
		public AbstractUser login(String username) {
			AbstractUser user = iUserRepository.findByUsername(username);
			if (user != null) {
				return user;
			} else {
				throw new IllegalArgumentException("Invalid username");
			}
		}
}
