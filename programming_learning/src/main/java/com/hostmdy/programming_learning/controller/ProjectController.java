package com.hostmdy.programming_learning.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.programming_learning.domain.Project;
import com.hostmdy.programming_learning.service.ProjectService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
	@RequestMapping("/show")
	public String showProject(Model model) {
		model.addAttribute("projects", projectService.getAllProject());
		return "project/project";
	}
	
	@GetMapping("/{projectId}/show")
	public String showCourseDetails(@PathVariable Long projectId, Model model) {
		Optional<Project> projectOpt = projectService.getProjectById(projectId);

		if (projectOpt.isEmpty()) {
			throw new NullPointerException("project is not found.");
		}
		model.addAttribute("project", projectOpt.get());
		return "project/project-video";
	}
	
	@GetMapping("/image/{projectId}/show")
	public void showCourseIamge(@PathVariable Long projectId, HttpServletResponse response) {
		Optional<Project> projectOpt = projectService.getProjectById(projectId);

		if (projectOpt.isEmpty()) {
			throw new RuntimeException("recipe is not found!");
		}

		Project project = projectOpt.get();

		byte[] imageBytes = new byte[project.getImage().length];

		int i = 0;
		for (final byte b : project.getImage()) {
			imageBytes[i++] = b;
		}

		InputStream ls = new ByteArrayInputStream(imageBytes);
		response.setContentType("image/png");

		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/createnew")
	public String createProjectForm(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);
		return "project/add-project";
	}
	
	@PostMapping("/createnew")
	public String createProject(@ModelAttribute Project project, @RequestParam MultipartFile imagefile)
			throws IOException {

		if (imagefile.getBytes().length > 0) {
			Byte[] imageBytes = new Byte[imagefile.getBytes().length];

			int i = 0;
			for (final Byte b : imagefile.getBytes()) {
				imageBytes[i++] = b;
			}

			project.setImage(imageBytes);
		}
		project.setReview(0.0);
		Project createdProject = projectService.createProject(project);
		return "redirect:/project/show";
	}

	@GetMapping("/{projectId}/update")
	public String updateCourseForm(@PathVariable Long projectId, Model model) {
		Optional<Project> projectOpt = projectService.getProjectById(projectId);

		if (projectOpt.isEmpty()) {
			throw new NullPointerException("project is not found!");
		}

		Project project = projectOpt.get();
		model.addAttribute("project", project);
		return "project/add-project"; 
	}
	
	@GetMapping("/{projectId}/delete")
	public String deleteProject(@PathVariable Long projectId) {
		projectService.deleteProjectById(projectId);
		return "redirect:/project/show";
	}

}
