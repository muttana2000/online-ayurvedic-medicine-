package com.cg.oam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.Entity.Prescription;

@Repository
public interface IPrescriptionRepository extends JpaRepository<Prescription, Integer> {

}
