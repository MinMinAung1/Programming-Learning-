package com.hostmdy.programming_learning.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.hostmdy.programming_learning.domain.Course;

public interface CourseService {
	
	Course saveCourse(Course course);
	
	Course createCourse(Course course) throws IOException;
	
	List<Course> getAllCourse();
	
	Optional<Course> getCourseById(Long courseId);
	
	List<Course> getCourseByName(String name);
	
	List<Course> getAllCourseByReview(Double review);
	
	void deleteCourseById(Long courseId);
}
