package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entity.Address;



@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer>{
   
	 public Address findByAddressId(Integer addressId);
}
