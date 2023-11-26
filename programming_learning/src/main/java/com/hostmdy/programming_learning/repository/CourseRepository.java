package com.hostmdy.programming_learning.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.programming_learning.domain.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{

	List<Course> findByName(String name);
	
	List<Course> findByReview(Double review);
}
