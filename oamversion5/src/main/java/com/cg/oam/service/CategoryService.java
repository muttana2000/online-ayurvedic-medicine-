package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.CategoryBean;
import com.cg.oam.bean.MedicineBean;
import com.cg.oam.entity.Category;
import com.cg.oam.entity.Medicine;
import com.cg.oam.exception.NoSuchElementException;
import com.cg.oam.repository.ICategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {
   @Autowired
   ICategoryRepository categoryRepository;
   
   @Transactional
   public CategoryBean addCategory(Category category) {
	   categoryRepository.save(category);
	   CategoryBean categoryBean = new CategoryBean(category,false);
	   return categoryBean;
   }
   @Transactional
   public CategoryBean getCategoryByCategoryName(String categoryName) {
	   Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
	   if(optionalCategory.isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   Category category = optionalCategory.get();
	   List<Medicine> medList = category.getMedicines();
	   List<MedicineBean> medBeanList = new ArrayList<>();
		medList.stream().forEach(medicine-> medBeanList.add(new MedicineBean(medicine,true,false)));
	   CategoryBean categoryBean = new CategoryBean(category,true);
	   categoryBean.setMedicines(medBeanList);
	   return categoryBean;
   }
   @Transactional
   public List<CategoryBean> getAllCategories(){
	   List<Category> categoryList = categoryRepository.findAll();
	   if(categoryList.isEmpty()) {
		   throw new NoSuchElementException();
	   }
	   List<CategoryBean> categoryBeanList = new ArrayList<>();
	   categoryList.stream().forEach(category-> categoryBeanList.add(new CategoryBean(category, false)));
	   return categoryBeanList;
   }
}
