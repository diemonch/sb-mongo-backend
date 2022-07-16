package com.api.withmongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.withmongo.exception.CourseCollectionException;
import com.api.withmongo.model.Courses;
import com.api.withmongo.repository.CourseRepository;


@Service
public class CourseServiceImplementor implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public void createCourse(Courses course) throws ConstraintViolationException, CourseCollectionException {
		// TODO Auto-generated method stub
		
		Optional<Courses> courseOptional= courseRepository.findbyCourse(course.getName());
		if(courseOptional.isPresent()) {
			System.out.println("----Exception Block ----Constraint Exception--");
			throw new 
			CourseCollectionException(CourseCollectionException.CourseAlreadyExistsException());
		}else {
			
			course.setCreateddate(new Date(System.currentTimeMillis()));
			courseRepository.save(course);
		}
		
	}

	@Override
	public List<Courses> getAllCourses() {
		// TODO Auto-generated method stub
		List<Courses> courseList = courseRepository.findAll();
		if(courseList.size()>0) {
			
			return courseList;
		}
		return new ArrayList<Courses>();
	}

	@Override
	public Courses getSingleCourse(String id) throws CourseCollectionException {
	
		Optional<Courses> findById = courseRepository.findById(id);
		
		if(!findById.isPresent()) {
			
			throw new CourseCollectionException(CourseCollectionException.CourseNotFoundException(id));
		} else {
			
			return findById.get();
		}
		
	}
	
	

}
