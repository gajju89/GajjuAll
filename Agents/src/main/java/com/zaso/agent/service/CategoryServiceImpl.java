package com.zaso.agent.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zaso.agent.dao.CategoryDao;
import com.zaso.agent.dao.CategoryDaoImpl;
import com.zaso.agent.model.Category;


@Repository
public class CategoryServiceImpl implements CategoryService {
	

	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    CategoryDao cat;
 
   
   @Autowired
   public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public CategoryServiceImpl()
    {
    	super();
    }

	@Override
	public void saveOrUpdate(Category category) {
		// TODO Auto-generated method stub
		cat.saveOrUpdate(category);
	}
	
	@Override
	public void DeleteCategory(String category)
	{
		cat.DeleteCategory(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cat.list();
	}

}
