package com.harshul.sample_bird_app.service;

import java.util.List;

import com.harshul.sample_bird_app.model.Bird;

/**
 * 
 * @author harshul varshney
 * Jun 6, 2017
 */
public interface IBirdAppService {

	/**
	 * Creates new bird entity in DB.
	 * @param bird
	 * @return
	 */
	public Bird addBird(Bird bird);

	/**
	 * Returns all bird entities from DB.
	 * @return
	 */
	public List<Bird> findAll();

	/**
	 * Returns a bird by the ID passed.
	 * @param id
	 * @return
	 */
	public Bird findById(String id);
	
	/**
	 * Deletes a bird recird by ID from DB.
	 * @param id
	 */
	public void delete(String id);
	
}
