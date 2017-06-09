package com.harshul.sample_bird_app;

import java.util.List;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.harshul.sample_bird_app.controller.BirdAppController;
import com.harshul.sample_bird_app.model.Bird;
import com.harshul.sample_bird_app.service.BirdAppService;


/**
 * Test class for BirdAppController.
 * 
 * @author harshul varshney
 * Jun 4, 2017
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class BirdAppControllerTest {

	private MockMvc mockMvc;
	@Mock BirdAppService birdAppService;
	@InjectMocks private BirdAppController birdAppController;
	
	@Before
    public void setup() {

        // this must be called for the @Mock annotations above to be processed
        // and for the mock service to be injected into the controller under
        // test.
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(birdAppController).build();
    }
	
	//======================GET all test================================================
	@Test
	public void testGetAllBirds() throws Exception {
		List<Bird> birds = TestUtil.createBirdListForTesting();
		Mockito.when(birdAppService.findAll()).thenReturn(birds);
		RequestBuilder request = MockMvcRequestBuilders.get("/birds");
		mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$", IsCollectionWithSize.hasSize(2)))
				.andDo(MockMvcResultHandlers.print())
		;
		
	}
	
	//====================create API test================================================
	@Test
	public void testAddBird() throws Exception {
		Bird bird1 = TestUtil.getBird1();
		
		//we will send bird-2 as json but we are mocking the response with bird-1
		//this is just to verify that mocking of our service is working
		String json = TestUtil.asJsonString(TestUtil.getBird2());
		
		Mockito.when(birdAppService.addBird(Mockito.any(Bird.class))).thenReturn(bird1);
		RequestBuilder request = MockMvcRequestBuilders.post("/birds")
													   .content(json)
													   .contentType(MediaType.APPLICATION_JSON)
													   .accept(MediaType.APPLICATION_JSON_UTF8);
		
		mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(bird1.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(bird1.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.family", Matchers.is(bird1.getFamily())));
		
		ArgumentCaptor<Bird> argCaptor = ArgumentCaptor.forClass(Bird.class);
		//Verify that the addBird() method is called only once and capture the object given as a parameter.
		Mockito.verify(birdAppService, Mockito.times(1)).addBird(argCaptor.capture());
		//Verify that the other methods of our mock object are not called during our test.
		Mockito.verifyNoMoreInteractions(birdAppService);
 
	}
	
	//========================get-by-id API test==========================================
	@Test
	public void testFindByIdSuccess() throws Exception {
		Bird bird = TestUtil.getBird1();
		Mockito.when(birdAppService.findById("MH-101")).thenReturn(bird);
		RequestBuilder request = MockMvcRequestBuilders.get("/birds/{id}", "MH-101");
		mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(MockMvcResultMatchers.model().attribute("bird", bird))
				.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id", Matchers.is(bird.getId())))
				.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name", Matchers.is(bird.getName())))
//				.andExpect(MockMvcResultMatchers.model().size(1))
				;
		System.out.println();
	}
	
	@Test
    public void testFindByIdFail() throws Exception {

		Mockito.when(birdAppService.findById("1")).thenReturn(null);
		RequestBuilder request = MockMvcRequestBuilders.get("/birds/{id}", "1");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
 	
	//=====================delete API test================================================
	@Test
    public void testDeleteBirdSuccess() throws Exception {
        Bird bird = TestUtil.getBird1();
		Mockito.when(birdAppService.findById(bird.getId())).thenReturn(bird);
		Mockito.doNothing().when(birdAppService).delete(bird.getId());

		RequestBuilder request = MockMvcRequestBuilders.delete("/birds/{id}", bird.getId());
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(birdAppService, Mockito.times(1)).findById(bird.getId());
        Mockito.verify(birdAppService, Mockito.times(1)).delete(bird.getId());
        Mockito.verifyNoMoreInteractions(birdAppService);
    }

    @Test
    public void testDeleteBirdFail() throws Exception {
    	Bird bird = TestUtil.getBird1();
		Mockito.when(birdAppService.findById(bird.getId())).thenReturn(null);

		RequestBuilder request = MockMvcRequestBuilders.delete("/birds/{id}", bird.getId());
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(birdAppService, Mockito.times(1)).findById(bird.getId());
        Mockito.verifyNoMoreInteractions(birdAppService);
    }
	

}
