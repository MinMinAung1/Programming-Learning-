package com.hostmdy.programming_learning.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.hostmdy.programming_learning.domain.Project;

public interface ProjectService {
	
Project saveProject(Project project);
	
	Project createProject(Project project) throws IOException;
	
	List<Project> getAllProject();
	
	Optional<Project> getProjectById(Long projectId);
	
	List<Project> getProjectByName(String name);
	
	void deleteProjectById(Long projectId);

}
