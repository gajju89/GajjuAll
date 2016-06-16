package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.Favourite;


@Repository
public interface FavouriteRepository extends MongoRepository<Favourite,String> {
	
	List<Favourite> findAll();

	Favourite insert(Favourite favourite);
	
	void deleteFavouriteByUseremail(String email);
	
	List<Favourite> findByUseremail(String email);
	void delete(Favourite favourite);
}
