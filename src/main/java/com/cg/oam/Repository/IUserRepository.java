package com.cg.oam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.oam.Entity.AbstractUser;

@Repository
public interface IUserRepository extends JpaRepository<AbstractUser, Integer> {
	
	@Query("SELECT m FROM AbstractUser m WHERE m.username = ?1")
	AbstractUser findByUsername(String username);
}
