package com.StokTakip.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StokTakip.Model.Category;


public interface CategoryDataRepository extends JpaRepository<Category, Integer>{
	
}
