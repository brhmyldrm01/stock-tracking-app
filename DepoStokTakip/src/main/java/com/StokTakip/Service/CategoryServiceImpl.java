package com.StokTakip.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StokTakip.Exception.Exceptions;
import com.StokTakip.Model.Category;
import com.StokTakip.Repository.CategoryDataRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDataRepository categoryDataRepository;
	
	
	
	@Override
	public void createCategory(Category category) {
		categoryDataRepository.save(category);
		
	}

	@Override
	public Optional<Category> getCategoryById(int id) throws Exceptions  {
		if(this.isExists(id))
		return categoryDataRepository.findById(id);
		else
			throw new Exceptions("Kategori Bulunamadı.");
	
	}

	@Override
	public void updateCategory(Category category)  throws Exceptions{
	 if(this.isExists(category.getId()))
                categoryDataRepository.save(category);
	 else	
		 throw new Exceptions("Güncellenecek Kategori Bulunamadi.");
	 
		
	}

	@Override
	public void deleteCategory(int id) throws Exceptions {
		 if(this.isExists(id))
		categoryDataRepository.deleteById(id);
		 else
			 throw new Exceptions("Silinecek Kategori Bulunamadı.");
	}


	@Override
	public List<Category> getAllCategories() {
		List<Category> list=categoryDataRepository.findAll();
		return list;
	}

	@Override
	public long getCategoryCount() {
		return categoryDataRepository.count();
	}

	@Override
	public boolean isExists(int id) {
	return categoryDataRepository.existsById(id);
		
	}

	

}
