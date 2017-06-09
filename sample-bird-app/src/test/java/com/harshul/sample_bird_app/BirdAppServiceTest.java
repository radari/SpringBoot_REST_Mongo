package com.harshul.sample_bird_app;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.harshul.sample_bird_app.model.Bird;
import com.harshul.sample_bird_app.repository.BirdRepository;
import com.harshul.sample_bird_app.service.BirdAppService;


/**
 * TEst class for IBirdAppService.
 * 
 * @author harshul varshney
 * Jun 8, 2017
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class BirdAppServiceTest {
	
	@InjectMocks private BirdAppService birdAppService;
	@Mock private BirdRepository birdRepository;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testAddBird() {
		Bird bird1 = TestUtil.getBird1();
		Mockito.when(birdRepository.save(Mockito.any(Bird.class))).thenReturn(bird1);
		
		Bird bird = birdAppService.addBird(bird1);
		Assert.assertEquals(bird.getId(), bird1.getId());
	}
	
	@Test
	public void testFindAll() {
		List<Bird> birds = TestUtil.createBirdListForTesting();
		Mockito.when(birdRepository.findAll()).thenReturn(birds);
		
		List<Bird> findAllResult = birdAppService.findAll();
		Assert.assertEquals(2, findAllResult.size());
	}
	
	@Test
	public void testFindById() {
		Bird bird1 = TestUtil.getBird1();
		Mockito.when(birdRepository.findOne(bird1.getId())).thenReturn(bird1);
		
		Bird findByIdResult = birdAppService.findById(bird1.getId());
		Assert.assertEquals(bird1.getId(), findByIdResult.getId());
		Assert.assertEquals(bird1.getName(), findByIdResult.getName());
	}
	
	@Test
	public void testDelete() {
		Bird bird1 = TestUtil.getBird1();
		Mockito.doNothing().when(birdRepository).delete(bird1.getId());
		birdAppService.delete(bird1.getId());
		Mockito.verify(birdRepository, Mockito.times(1)).delete(bird1.getId());
	}
}
