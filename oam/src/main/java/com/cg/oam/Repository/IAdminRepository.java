package com.cg.oam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Integer> {
	
}
