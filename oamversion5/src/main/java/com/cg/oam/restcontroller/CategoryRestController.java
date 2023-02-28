package com.cg.oam.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.entity.Category;
import com.cg.oam.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
   @Autowired
   private CategoryService categoryService;
   
   @PostMapping("/addCategory")
   public ResponseEntity<String> addcategory(@RequestBody Category category){
	   categoryService.addCategory(category);
	   return ResponseEntity.ok("Category added successfully");
   }
}
