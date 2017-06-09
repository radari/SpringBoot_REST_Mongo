package com.harshul.sample_bird_app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshul.sample_bird_app.model.Bird;

/**
 * 
 * @author harshul varshney
 * Jun 6, 2017
 */
public class TestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),                        
            Charset.forName("utf8")                     
            );
	
	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
 
    public static String createStringWithLength(int length) {
        StringBuilder builder = new StringBuilder();
 
        for (int index = 0; index < length; index++) {
            builder.append("a");
        }
 
        return builder.toString();
    }
	
	public static String asJsonString(final Object obj) {
	    try {
	    	final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static List<Bird> createBirdListForTesting() {
		List<Bird> birds = new ArrayList<>();
		birds.add(getBird1());
		birds.add(getBird2());
		return birds;
	}

	public static Bird getBird2() {
		Bird bird2 = new Bird();
		bird2.setId("MH-102");
		bird2.setName("bird-2");
		bird2.setFamily("chinese");
		bird2.setVisible(false);
		Set<String> continents2 = new HashSet<>();
		continents2.add("continent-1");
		bird2.setContinents(continents2);
		return bird2;
	}

	public static Bird getBird1() {
		Bird bird1 = new Bird();
		bird1.setId("MH-101");
		bird1.setName("bird-1");
		bird1.setFamily("indian");
		bird1.setVisible(true);
		Set<String> continents = new HashSet<>();
		continents.add("continent-1"); continents.add("continent-2");
		bird1.setContinents(continents);
		return bird1;
	}
}
