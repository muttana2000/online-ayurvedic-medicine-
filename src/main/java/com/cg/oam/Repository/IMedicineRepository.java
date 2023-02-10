package com.cg.oam.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.oam.Entity.Medicine;

@Repository
public interface IMedicineRepository extends JpaRepository<Medicine,Integer>{
	    //method to find medicine by its id
		public Medicine findByMedicineId(Integer medicineId);
		
		//method to find medicine by its name
		public Medicine findByMedicineName(String medicineName);
		
		//method to find all medicines of a specific category
		@Query("SELECT m FROM Medicine m WHERE m.medicineCategory = ?1")
		public List<Medicine> findAllMedicinesByCategory(String categoryName);
		
		//method to find all medicines of a specific company
		@Query("SELECT m FROM Medicine m WHERE m.companyName = ?1")
		public List<Medicine> findAllMedicinesByCompany(String companyName);
		
		
		//method to find all medicines which are in stock
		@Query("SELECT m FROM Medicine m WHERE m.stock > 0")
		public List<Medicine> findAllAvailableMedicines();
}
