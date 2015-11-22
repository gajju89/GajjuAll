package com.zaso.agent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.Category;
@Repository
public class CategoryDaoImpl implements CategoryDao {
	
	private JdbcTemplate jdbcTemplate;
	 
	  
    public CategoryDaoImpl()
    {
    	super();
    }
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    

	@Override
	public void saveOrUpdate(Category category) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO category (objectid,categoryname)"
				+ " VALUES (?, ?)";
		jdbcTemplate.update(sql, category.getObjectid(),category.getCategoryname());

	}
	
	 @Override
	  	public void DeleteCategory(String category) {
	  		// implementation details goes here...
	  		String sql = "DELETE FROM category WHERE categoryname ="+category;
	  		jdbcTemplate.update(sql);

	  	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM category";
		List<Category> category = jdbcTemplate.query(sql, new RowMapper<Category>() {

			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category1 = new Category();
				category1.setObjectid(rs.getString("objectid"));
				category1.setCategoryname(rs.getString("categoryname"));
				
		return category1 ;
	}
		
	});
	return category;
}


}
