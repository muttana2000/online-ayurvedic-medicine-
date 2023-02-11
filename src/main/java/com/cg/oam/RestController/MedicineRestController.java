package com.cg.oam.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.Bean.MedicineBean;
import com.cg.oam.Service.MedicineService;
@RestController
@RequestMapping("/medicine")
public class MedicineRestController {
	@Autowired
	private MedicineService medicineService;
	
	@PostMapping("/addMedicine")
	public ResponseEntity<String> addMedicine(@RequestBody MedicineBean med)
	{
		medicineService.createMedicine(med);
		return ResponseEntity.ok("Medicine Saved");
	}
	
	@PutMapping("/updateMedicine")
	public ResponseEntity<String> updateMedicine(@RequestBody MedicineBean med)
	{
		medicineService.updateMedicine(med);
		return ResponseEntity.ok("Medicine updated");
	}
	
	@DeleteMapping("/deleteMedicine/{medicineid}")
	public ResponseEntity<Void> deleteMedicine(@PathVariable("medicineid") int medicineid)
	{
		medicineService.deleteMedicine(medicineid);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllMedicines")
	public ResponseEntity<List<MedicineBean>> findAllMedicines()
	{
		List<MedicineBean> medicines=medicineService.findAllAvailableMedicines();
		return ResponseEntity.ok(medicines);
	}
	
	@GetMapping("/getmedicinebyid/{medicineid}")
	public ResponseEntity<MedicineBean> findByMedicineId(@PathVariable("medicineid") int medicineid)
	{
		MedicineBean medRetrieved=medicineService.getMedicineById(medicineid);
		return ResponseEntity.ok(medRetrieved);
	}
	
	@GetMapping("/getByMedicineName/{medicinename}")
	public ResponseEntity<MedicineBean> findByMedicineName(@PathVariable("medicinename") String medicineName)
	{
		MedicineBean medicine=medicineService.findByMedicineName(medicineName);
		return ResponseEntity.ok(medicine);
	}
	
	@GetMapping("/getByMedicineCategory/{categoryname}")
	public ResponseEntity<List<MedicineBean>> findAllMedicinesByCategory(@PathVariable("categoryname") String categoryName)
	{
		List<MedicineBean> medicines=medicineService.findByMedicineCategory(categoryName);
		return ResponseEntity.ok(medicines);
	}
	
	@GetMapping("/getMedicinebyCompanyName/{companyname}")
	public ResponseEntity<List<MedicineBean>> findAllMedicinesByCompany(@PathVariable("companyname") String companyName)
	{
		List<MedicineBean> medicines=medicineService.findByCompany(companyName);
		return ResponseEntity.ok(medicines);
	}
}
