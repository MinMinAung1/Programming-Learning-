package com.hostmdy.programming_learning.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.programming_learning.domain.Course;
import com.hostmdy.programming_learning.repository.CourseRepository;
import com.hostmdy.programming_learning.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public Course createCourse(Course course) throws IOException {
		// TODO Auto-generated method stub
		return saveCourse(course);
	}

	@Override
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return (List<Course>) courseRepository.findAll();
	}

	@Override
	public Optional<Course> getCourseById(Long courseId) {
		// TODO Auto-generated method stub
		return courseRepository.findById(courseId);
	}

	@Override
	public List<Course> getCourseByName(String name) {
		// TODO Auto-generated method stub
		return courseRepository.findByName(name);
	}

	@Override
	public List<Course> getAllCourseByReview(Double review) {
		// TODO Auto-generated method stub
		return courseRepository.findByReview(review);
	}

	@Override
	public void deleteCourseById(Long courseId) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(courseId);

	}

}
