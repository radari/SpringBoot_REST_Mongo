package com.harshul.sample_bird_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.harshul.sample_bird_app.model.Bird;
import com.harshul.sample_bird_app.service.IBirdAppService;

/**
 * Contains the rest end-points for below CRUD operations:
 * 		GET
 * 		POST
 * 		DELETE
 * 
 * @author harshul varshney
 * Jun 4, 2017
 */
@RestController
@RequestMapping(value = "/birds")
public class BirdAppController {
	
	@Autowired private IBirdAppService birdService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Bird>> getAllBirds() {
		List<Bird> birds = null;
		try {
			birds = birdService.findAll();
			return ResponseEntity.ok(birds);
		} catch(Exception e) {
			System.out.println("Exception occured " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Bird> addBird(@RequestBody Bird bird) {
		try {
			bird = birdService.addBird(bird);
			if(bird != null)
				return ResponseEntity.status(HttpStatus.CREATED).body(bird);
		} catch(Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Bird> findById(@PathVariable("id") String id) {
		Bird bird = null;
		try {
			bird = birdService.findById(id);
			if(bird != null)
				return ResponseEntity.status(HttpStatus.OK).body(bird);
		} catch(Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity deleteBird(@PathVariable("id") String id) {
		try {
			Bird bird = birdService.findById(id);
			if(bird != null) {
				birdService.delete(id);
				return ResponseEntity.status(HttpStatus.OK).body(null);
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch(Exception e) {
			System.out.println("Exception occured: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
