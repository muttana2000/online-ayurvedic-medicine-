package com.cg.oam.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
	public Optional<Customer> findByUsername(String username);
    public Customer findByUserId(Integer userId);
}
