package com.api.withmongo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="courses")

public class Courses {
	
	@Id
	private String id;
	
	@NotNull(message="Course name can not be null")
	private String name;
	
	@NotNull(message="Description name can not be null")
	private String description;
	
	@NotNull(message="Author name can not be null")
	private String author;
	
	@NotNull(message="Duration name can not be null")
	private int duration;
	
	private Date createddate;
	
	private Date udateddate;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUdateddate() {
		return udateddate;
	}

	public void setUdateddate(Date udateddate) {
		this.udateddate = udateddate;
	}

	public Courses(String id, String name, String description, String author, int duration, Date createddate,
			Date udateddate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.author = author;
		this.duration = duration;
		this.createddate = createddate;
		this.udateddate = udateddate;
	}
	
	public Courses() {
	
	}
	

}
