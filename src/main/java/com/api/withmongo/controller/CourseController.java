package com.api.withmongo.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.withmongo.exception.CourseCollectionException;
import com.api.withmongo.model.Courses;
import com.api.withmongo.repository.CourseRepository;
import com.api.withmongo.service.CourseService;



@RestController
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public ResponseEntity<?> getAllCourses() {
		
		List<Courses> courseList = courseService.getAllCourses();
		return new ResponseEntity<>(courseList,courseList.size() > 0?
				HttpStatus.OK:HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<?> getOneCourse(@PathVariable("id") String id) {
		
		try {
			return new ResponseEntity<>(courseService.getSingleCourse(id),HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
				
		}
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable("id") String id, @RequestBody Courses course) {
			
		try {
			courseService.updateCourse(id, course);
			return new ResponseEntity<>("Course details of Id: "+id+" has been updated", HttpStatus.OK);
			
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
			
		} catch(CourseCollectionException e) {
			
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/course")
	public ResponseEntity<?> postCourse(@RequestBody Courses course)
	{
		
		try {
			//Old method, directly inserting into database
			//course.setCreateddate(new Date(System.currentTimeMillis()));
			//courseRepo.save(course);
			courseService.createCourse(course);
			
			return new ResponseEntity<Courses>(course,HttpStatus.OK);
		}catch(ConstraintViolationException e) {
			
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}catch(CourseCollectionException e)
		{
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/course/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id)
	{
		
		try {
			
			courseService.deleteCourse(id);
			return new ResponseEntity<>("Course Details Has been deleted for id: "+id,HttpStatus.OK);
		}catch(CourseCollectionException e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
	}
	

}
