package com.cg.oam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.oam.Entity.Address;



@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer>{

}
