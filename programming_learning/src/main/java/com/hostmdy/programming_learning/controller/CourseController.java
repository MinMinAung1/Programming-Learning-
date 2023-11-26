package com.hostmdy.programming_learning.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

import com.hostmdy.programming_learning.domain.Account;
import com.hostmdy.programming_learning.domain.Course;
import com.hostmdy.programming_learning.service.CourseService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/course")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@GetMapping("/{courseId}/show")
	public String showCourseDetails(@PathVariable Long courseId, Model model) {
		Optional<Course> courseOpt = courseService.getCourseById(courseId);

		if (courseOpt.isEmpty()) {
			throw new NullPointerException("course is not found.");
		}
		model.addAttribute("course", courseOpt.get());
		return "video/video";
	}

	@GetMapping("/new")
	public String createCourseForm(Model model) {

		Course course = new Course();
		model.addAttribute("course", course);
		return "course/add-course";
	}

	@PostMapping("/new")
	public String createCourse(@ModelAttribute Course course, @RequestParam MultipartFile imagefile)
			throws IOException {

		if (imagefile.getBytes().length > 0) {
			Byte[] imageBytes = new Byte[imagefile.getBytes().length];

			int i = 0;
			for (final Byte b : imagefile.getBytes()) {
				imageBytes[i++] = b;
			}

			course.setImage(imageBytes);
		}
		course.setReview(0.0);
		Course createdCourse = courseService.createCourse(course);
		return "redirect:/";
	}

	@GetMapping("/image/{courseId}/show")
	public void showCourseIamge(@PathVariable Long courseId, HttpServletResponse response) {
		Optional<Course> courseOpt = courseService.getCourseById(courseId);

		if (courseOpt.isEmpty()) {
			throw new RuntimeException("recipe is not found!");
		}

		Course course = courseOpt.get();

		byte[] imageBytes = new byte[course.getImage().length];

		int i = 0;
		for (final byte b : course.getImage()) {
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

	@GetMapping("/{courseId}/update")
	public String updateCourseForm(@PathVariable Long courseId, Model model) {
		Optional<Course> courseOpt = courseService.getCourseById(courseId);

		if (courseOpt.isEmpty()) {
			throw new NullPointerException("course is not found!");
		}

		Course course = courseOpt.get();
		model.addAttribute("course", course);
		return "course/add-course";
	}

	@GetMapping("/{courseId}/delete")
	public String deleteCourse(@PathVariable Long courseId) {
		courseService.deleteCourseById(courseId);
		return "redirect:/";
	}

	@PostMapping("/search")
	public String searchCourse(@RequestParam String name, Model model) {
		List<Course> courseList = courseService.getCourseByName(name);

		if (courseList.isEmpty()) {
			model.addAttribute("courses", null);
		}
		model.addAttribute("courses", courseList);
		return "/index";
	}

}
