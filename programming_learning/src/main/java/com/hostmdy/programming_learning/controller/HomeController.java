package com.hostmdy.programming_learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hostmdy.programming_learning.domain.Account;
import com.hostmdy.programming_learning.service.CourseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	private final CourseService courseService;

	public HomeController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@GetMapping({ "/", "/index", "/home" })
	public String showHome(Model model) {
		model.addAttribute("courses", courseService.getAllCourse());
		return "index";
	}
	
	@GetMapping({"/login"})
	public String loginForm(Model model) {
		model.addAttribute("account", new Account());
		return "common/login";
	}
	
	@GetMapping({"/register"})
	public String signupForm() {
		return "common/register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
		
	}
}
