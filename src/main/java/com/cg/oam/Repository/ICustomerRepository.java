package com.cg.oam.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.oam.Entity.Customer;


public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
    Customer findByUserId(Integer userId);
}
