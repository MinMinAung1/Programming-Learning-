package com.hostmdy.programming_learning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.programming_learning.domain.Course;
import com.hostmdy.programming_learning.domain.Video;

public interface VideoRepository extends CrudRepository<Video, Long>{

	List<Video> findByCourse(Course course);
	
	Optional<Video> findByName(String name);
}
