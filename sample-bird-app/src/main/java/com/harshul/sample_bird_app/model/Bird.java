package com.harshul.sample_bird_app.model;

import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author harshul varshney
 * Jun 4, 2017
 */
public class Bird {
	
	@Id private String id;
	
	@NotNull
	private String name;
	@NotNull
	private String family;
	@NotNull
	private Set<String> continents;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date added = new Date();
	private boolean visible;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public Set<String> getContinents() {
		return continents;
	}
	public void setContinents(Set<String> continents) {
		this.continents = continents;
	}
	public Date getAdded() {
		return added;
	}
	public void setAdded(Date added) {
		this.added = added;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
