package com.zaso.mongo.repositories;


	import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
	import org.springframework.stereotype.Repository;

//import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.zaso.mongo.model.MongoAgent;
import org.springframework.data.geo.Point;



	@Repository
	
	public interface AgentRepository extends MongoRepository<MongoAgent, String> {

	
		List<MongoAgent> findByLocationNear(Point location, Distance distance);
	}
