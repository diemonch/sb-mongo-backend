package com.api.withmongo.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.api.withmongo.exception.CourseCollectionException;
import com.api.withmongo.model.Courses;

public interface CourseService {

	public void createCourse(Courses course) throws ConstraintViolationException, CourseCollectionException;
	
	public List<Courses> getAllCourses();
	
}
