package com.cg.oam.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.bean.CategoryBean;
import com.cg.oam.entity.Category;
import com.cg.oam.service.CategoryService;

@CrossOrigin(origins="http://localhost:3000")
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
   @GetMapping("/{categoryName}")
   public ResponseEntity<CategoryBean> getCategoryByCategoryName(@PathVariable String categoryName){
	   CategoryBean categoryBean = categoryService.getCategoryByCategoryName(categoryName);
	   return ResponseEntity.ok(categoryBean);
   }
   @GetMapping("/all")
   public ResponseEntity<List<CategoryBean>> getAllcategories(){
	   List<CategoryBean> categoryBeans = categoryService.getAllCategories();
	   return ResponseEntity.ok(categoryBeans);
   }
   
}
