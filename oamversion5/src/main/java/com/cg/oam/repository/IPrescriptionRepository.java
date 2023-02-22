package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entity.Prescription;

@Repository
public interface IPrescriptionRepository extends JpaRepository<Prescription, Integer> {

}
