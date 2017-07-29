package com.zaso.agent.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.zaso.agent.model.Category;
import com.zaso.agent.repositories.CategoryRepository;


@Repository
public class CategoryServiceImpl implements CategoryService {
	

	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    CategoryRepository cateRepo;
 
   
 /*  @Autowired
   public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public CategoryServiceImpl()
    {
    	super();
    }
*/
	@Override
	public void saveOrUpdate(Category category) {
		// TODO Auto-generated method stub
		cateRepo.save(category);
	}
	
	@Override
	public void DeleteCategory(String category)
	{
		cateRepo.deleteCategoryByCategoryname(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cateRepo.findAll();
	}

}
