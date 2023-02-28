package com.cg.oam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.oam.bean.CategoryBean;
import com.cg.oam.entity.Category;
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
}
