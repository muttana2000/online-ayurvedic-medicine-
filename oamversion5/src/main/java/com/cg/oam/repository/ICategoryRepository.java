package com.cg.oam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.oam.entity.Category;
import com.cg.oam.entity.Medicine;

public interface ICategoryRepository extends JpaRepository<Category,Integer>{
	
	public Optional<Category> findByCategoryName(String categoryName);
	
	@Query("SELECT m FROM Category m WHERE m.categoryName = ?1")
    public List<Medicine> findAllMedicinesByCategoryName(String categoryName);
}
