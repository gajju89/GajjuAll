package com.zaso.agent.service;

import java.util.List;

import com.zaso.agent.model.Category;

public interface CategoryService {

	
	
 public void saveOrUpdate(Category category);
 
 public void DeleteCategory(String category);
     
	 public List<Category> list();

}
