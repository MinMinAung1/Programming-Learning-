package com.hostmdy.programming_learning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.programming_learning.domain.PjVideo;
import com.hostmdy.programming_learning.domain.Project;

public interface PjVideoRepository extends CrudRepository<PjVideo, Long>{
	
	List<PjVideo> findByProject(Project project);
	
	Optional<PjVideo> findByName(String name);

}
