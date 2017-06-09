package com.harshul.sample_bird_app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.harshul.sample_bird_app.model.Bird;

/**
 * 
 * @author harshul varshney
 * Jun 4, 2017
 */
@RepositoryRestResource(collectionResourceRel = "birds", path = "birds")
public interface BirdRepository extends MongoRepository<Bird, String>{

	List<Bird> findAll();
	
	Bird findOne(String id);
	
	Bird save(Bird bird);
	
	void delete(String biridd);
}
