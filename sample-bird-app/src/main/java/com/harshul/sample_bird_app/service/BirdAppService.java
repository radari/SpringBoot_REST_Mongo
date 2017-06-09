package com.harshul.sample_bird_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshul.sample_bird_app.model.Bird;
import com.harshul.sample_bird_app.repository.BirdRepository;

/**
 * 
 * @author harshul varshney
 * Jun 4, 2017
 */
@Service
public class BirdAppService implements IBirdAppService {
	
	@Autowired private BirdRepository birdRepository;
	
	@Override
	public Bird addBird(Bird bird) {
		return birdRepository.save(bird);
	}

	@Override
	public List<Bird> findAll() {
		return birdRepository.findAll();
	}

	@Override
	public Bird findById(String id) {
		return birdRepository.findOne(id);
	}
	
	@Override
	public void delete(String id) {
		birdRepository.delete(id);
	}
	

}
