package com.zaso.agent.dao;

import java.util.List;


import com.zaso.agent.model.Category;

public interface CategoryDao {
	
	 public void saveOrUpdate(Category category);
	 
	 public void DeleteCategory(String category);
     
	 public List<Category> list();

}
