package com.hostmdy.programming_learning.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.hostmdy.programming_learning.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

	List<Project> findByName(String name);
}
