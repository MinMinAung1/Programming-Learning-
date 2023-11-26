package com.hostmdy.programming_learning.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.programming_learning.domain.Project;
import com.hostmdy.programming_learning.repository.ProjectRepository;
import com.hostmdy.programming_learning.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Project saveProject(Project project) {
		// TODO Auto-generated method stub
		return projectRepository.save(project);
	}

	@Override
	public Project createProject(Project project) throws IOException {
		// TODO Auto-generated method stub
		return saveProject(project);
	}

	@Override
	public List<Project> getAllProject() {
		// TODO Auto-generated method stub
		return (List<Project>) projectRepository.findAll();
	}

	@Override
	public Optional<Project> getProjectById(Long projectId) {
		// TODO Auto-generated method stub
		return projectRepository.findById(projectId);
	}

	@Override
	public List<Project> getProjectByName(String name) {
		// TODO Auto-generated method stub
		return projectRepository.findByName(name);
	}

	@Override
	public void deleteProjectById(Long projectId) {
		// TODO Auto-generated method stub
		projectRepository.deleteById(projectId);

	}

}
