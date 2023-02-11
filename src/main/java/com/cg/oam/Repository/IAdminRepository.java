package com.cg.oam.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.Entity.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Integer> {
	
}
