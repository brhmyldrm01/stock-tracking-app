package com.StokTakip.Service;

import java.util.List;
import java.util.Optional;

import com.StokTakip.Exception.Exceptions;
import com.StokTakip.Model.Category;




public interface CategoryService {

	public void createCategory(Category category);
	public Optional<Category> getCategoryById(int id) throws Exceptions;
	public void updateCategory(Category category)  throws Exceptions;
	public void deleteCategory(int id) throws Exceptions;
	public List<Category> getAllCategories();
	public long getCategoryCount();
	public boolean isExists(int id);
}
