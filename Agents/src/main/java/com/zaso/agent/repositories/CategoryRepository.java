package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
	
	List<Category> findAll();
	
	Category save(Category category);
	
	Category deleteCategoryByCategoryname(String category);

}
