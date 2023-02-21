package com.cg.oam.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.oam.entity.AbstractUser;
import com.cg.oam.repository.IUserRepository;

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
