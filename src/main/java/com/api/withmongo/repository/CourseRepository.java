package com.api.withmongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.withmongo.model.Courses;

@Repository
public interface CourseRepository extends MongoRepository <Courses,String>{
	
	
	@Query("{'name':?0}")
	Optional<Courses> findbyCourse(String name);
	
}
