package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.MedicineBean;
import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.EmptyInputException;
import com.cg.oam.exception.EmptyResultDataAccessException;
import com.cg.oam.exception.InvalidInputException;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.helper.MedicineHelper;
import com.cg.oam.repository.ICategoryRepository;
import com.cg.oam.repository.IDescriptionRepository;
import com.cg.oam.repository.IMedicineRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicineService {

	@Autowired
	private IMedicineRepository medicineRepository;
	
	@Autowired
	private IDescriptionRepository descriptionRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	// here we are giving basic CRUD operations
	// Business logic and main functionalities also

	// method to create Medicine product
	// Admin can add and create medicine
	@Transactional
	public Medicine createMedicine(Medicine medicine) {
		if (medicine.getMedicineName() == null || medicine.getMedicineName().length() == 0
				|| medicine.getCompanyName() == null || medicine.getCompanyName().length() == 0) {
			throw new EmptyInputException();
		}
		if (medicine.getStock() == 0 || medicine.getStock() < 0 || medicine.getRating() == 0
				|| medicine.getRating() < 0) {
			throw new InvalidInputException();
		}
//		if (medicine.getManufactureDate().getYear() < 1 || medicine.getManufactureDate().getMonthValue() < 1
//				|| medicine.getManufactureDate().getMonthValue() > 12
//				|| medicine.getManufactureDate().getDayOfMonth() < 1
//				|| medicine.getManufactureDate().getDayOfMonth() > 31) {
//			throw new IllegalArgumentException("Invalid date");
//		}
        descriptionRepository.save(medicine.getDescription());
        categoryRepository.save(medicine.getCategory());
		return medicineRepository.save(medicine);
	}

	// method to update medicine details
	@Transactional
	public Medicine updateMedicine(MedicineBean medicine) {
		Medicine med = new Medicine(medicine);
		return medicineRepository.save(med);
	}

	// method to delete medicine by admin if medicine has new version
	@Transactional
	public void deleteMedicine(Integer medicineId) {
		Optional<Medicine> medicine = medicineRepository.findById(medicineId);
		if (medicine == null) {
			throw new NoSuchElementException();
		}
		medicineRepository.deleteById(medicineId);
	}

	// read the medicine by Id
	@Transactional
	 public MedicineBean getMedicineById(int id) {
	 Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
	 if(optionalMedicine.isEmpty()) {
		 throw new NoSuchElementException();
	 }
	 Medicine medicine = medicineRepository.findByMedicineId(id);
	 MedicineBean bean = new MedicineBean(medicine,true,true);
	 return bean;
	 }
	// all medicines
	
	 @Transactional public List<MedicineBean> getAllMedicines() { List<Medicine>
	 medicines= medicineRepository.findAll();
	  
	  List<MedicineBean> beanMedList = new ArrayList<>();
	  medicines.stream().forEach(med->beanMedList.add(new MedicineBean(med,true,false)));
	  if(beanMedList.isEmpty()) { throw new
	  EmptyResultDataAccessException(); }
	  return beanMedList; 
	  } 
	 //method to find
	 
	// medicine by its name

	@Transactional
	public MedicineBean findByMedicineName(String medicineName) {
		MedicineBean checkMedicineBean = MedicineHelper
				.generateMedicineBean(medicineRepository.findByMedicineName(medicineName));
		if (checkMedicineBean == null) {
			throw new NoSuchElementException();
		}
		return MedicineHelper.generateMedicineBean(medicineRepository.findByMedicineName(medicineName));
	}

	// method to find medicine by its category public
	public List<MedicineBean> findByMedicineCategory(String medicineCategory) {
		List<Medicine> medList = medicineRepository.findAllMedicinesByCategory(medicineCategory);
		if (medList.isEmpty()) {
			throw new EmptyResultDataAccessException();
		}
		List<MedicineBean> medBeanList = new ArrayList<>();
		medList.stream().forEach(med -> MedicineHelper.generateMedicineBean(med));
		return medBeanList;
	} // method to find all medicines its company name

	@Transactional
	public List<MedicineBean> findByCompany(String companyName) {
		List<Medicine> medList = medicineRepository.findAllMedicinesByCompany(companyName);
		if (medList.isEmpty()) {
			throw new EmptyResultDataAccessException();
		}
		List<MedicineBean> medBeanList = new ArrayList<>();
		medList.stream().forEach(med -> medBeanList.add(new MedicineBean(med,true, false)));
		return medBeanList;
	}

	// method to find all medicines which are available means stock>0
	@Transactional
	public List<MedicineBean> findAllAvailableMedicines() {
		List<Medicine> medList = medicineRepository.findAllAvailableMedicines();
		if (medList.isEmpty()) {
			throw new EmptyResultDataAccessException();
		}
		List<MedicineBean> medBeanList = new ArrayList<>();
		medList.stream().forEach(med -> MedicineHelper.generateMedicineBean(med));
		return medBeanList;
	}
	/*
	 * @Autowired private IDescriptionRepository descriptionRepository;
	 * 
	 * @Transactional public Description createDescription(DescriptionBean
	 * descriptionBean) { Description description= new Description(descriptionBean);
	 * if(description.getDetails() == null || description.getDetails().length()==0
	 * || description.getIngredients() == null||
	 * description.getIngredients().length()==0) { throw new EmptyInputException();
	 * } return descriptionRepository.save(description); }
	 * 
	 * @Transactional public Description updateDescription(DescriptionBean
	 * descriptionBean) { Description description= new Description(descriptionBean);
	 * return descriptionRepository.save(description); }
	 * 
	 * @Transactional public Optional<Description> findById(Integer descriptionId) {
	 * Optional<Description> desc = descriptionRepository.findById(descriptionId);
	 * if (desc == null) { throw new NoSuchElementException(); } return
	 * descriptionRepository.findById(descriptionId); }
	 */

}