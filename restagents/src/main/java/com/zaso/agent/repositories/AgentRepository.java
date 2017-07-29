package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
	import org.springframework.stereotype.Repository;

//import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.zaso.agent.model.MongoAgent;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.data.jpa.repository.Query;



	@Repository
	
	public interface AgentRepository extends MongoRepository<MongoAgent, String> {

	
		List<MongoAgent> findByLocationNear(Point location, Distance distance);
		
		List<MongoAgent> findAll();
		 
		MongoAgent insert(MongoAgent mongoAgent);
		
		MongoAgent deleteByEmailid(String email);
		
		MongoAgent findByEmailid(String email);

		List<MongoAgent> findByLocationWithin(Polygon polygon);
	}