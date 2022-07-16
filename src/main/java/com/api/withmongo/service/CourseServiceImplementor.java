package com.api.withmongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Override
	public void updateCourse(String id, Courses course) throws CourseCollectionException {
		
		Optional<Courses> courseID = courseRepository.findById(id);
		Optional<Courses> courseWithSameName = courseRepository.findbyCourse(course.getName());
		
		if(courseID.isPresent()) {
			
			if(courseWithSameName.isPresent() && !courseWithSameName.get().getId().equals(id))
			{
				throw new CourseCollectionException(
						CourseCollectionException.CourseAlreadyExistsException());
				
			}
			
			Courses courseToSave=courseID.get();
			courseToSave.setName(course.getName());
			courseToSave.setAuthor(course.getAuthor());
			courseToSave.setDescription(course.getDescription());
			courseToSave.setDuration(course.getDuration());
			courseToSave.setUdateddate(course.getUdateddate());
			courseRepository.save(courseToSave);
			
		}
		else {
			
			throw new CourseCollectionException(CourseCollectionException.CourseNotFoundException(id));

		}
		
	}
	
	

}
