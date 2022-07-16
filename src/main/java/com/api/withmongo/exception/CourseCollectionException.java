package com.api.withmongo.exception;

public class CourseCollectionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseCollectionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public static String CourseNotFoundException(String id)
	{
		return "Course with"+id+"Not found";
		
	}
	
	public static String CourseAlreadyExistsException()
	{
		return "Course name is already found, please provide another name";
		
	}
	
	
	
}
