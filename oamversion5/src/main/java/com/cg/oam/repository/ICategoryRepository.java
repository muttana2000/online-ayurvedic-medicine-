package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.oam.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category,Integer>{

}
