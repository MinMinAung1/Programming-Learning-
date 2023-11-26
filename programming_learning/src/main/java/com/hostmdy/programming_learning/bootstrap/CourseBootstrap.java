package com.hostmdy.programming_learning.bootstrap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.programming_learning.domain.Account;
import com.hostmdy.programming_learning.domain.Course;
import com.hostmdy.programming_learning.domain.PjVideo;
import com.hostmdy.programming_learning.domain.Project;
import com.hostmdy.programming_learning.domain.Video;
import com.hostmdy.programming_learning.repository.AccountRepository;
import com.hostmdy.programming_learning.repository.CourseRepository;
import com.hostmdy.programming_learning.repository.PjVideoRepository;
import com.hostmdy.programming_learning.repository.ProjectRepository;
import com.hostmdy.programming_learning.repository.VideoRepository;

@Component
public class CourseBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	public CourseRepository courseRepository;

	@Autowired
	public VideoRepository videoRepository;

	@Autowired
	public ProjectRepository projectRepository;

	@Autowired
	public AccountRepository accountRepository;

	@Autowired
	public PjVideoRepository pjVideoRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		courseRepository.saveAll(createCourse());
		courseRepository.saveAll(createCourse2());
		courseRepository.saveAll(createCourseJS());
		courseRepository.saveAll(createCourseHtml());
		accountRepository.save(createAccount());
		projectRepository.saveAll(createProject());
		projectRepository.saveAll(createProject1());
	}

	private List<Course> createCourse() {

		Course course = new Course();
		// image
		File imageFile = new File("C:/Users/Acer Nitro 5/Downloads/java.png");
		course.setImage(getByteArray(imageFile));
		course.setName("Java");
		course.setDescription("These Java lessions are for beginner.");
		course.setReview(0.0);

		// video
		File file = new File("C:/Users/Acer Nitro 5/Downloads/programmingIntro.mp4");

		Video java1 = new Video("1 Java Basic Introduction", getByteArray(file), course);
		Video java2 = new Video("2 First Program", getByteArray(file), course);
		Video java3 = new Video("3 Variable", getByteArray(file), course);
		Video java4 = new Video("4 DataType", getByteArray(file), course);

		// course - videos
		course.getVideos().add(java1);
		course.getVideos().add(java2);
		course.getVideos().add(java3);
		course.getVideos().add(java4);

		List<Course> courses1 = new ArrayList<>();
		courses1.add(course);

		return courses1;
	}

	private List<Course> createCourse2() {

		Course course = new Course();
		// image
		File imageFile = new File("C:/Users/Acer Nitro 5/Downloads/python.png");
		course.setImage(getByteArray(imageFile));
		course.setName("Python");
		course.setDescription("These Python lessions are for beginner.");
		course.setReview(4.0);

		File file = new File("C:/Users/Acer Nitro 5/Downloads/python.mp4");

		// video
		Video python1 = new Video("1 Python Basic Introduction", getByteArray(file), course);
		Video python2 = new Video("2 Python Basic First Program", getByteArray(file), course);
		Video python3 = new Video("3 Python Basic Variable", getByteArray(file), course);
		Video python4 = new Video("4 Python Basic DataType", getByteArray(file), course);

		course.getVideos().add(python1);
		course.getVideos().add(python2);
		course.getVideos().add(python3);
		course.getVideos().add(python4);

		List<Course> courses2 = new ArrayList<>();
		courses2.add(course);

		return courses2;

	}

	private List<Course> createCourseJS() {

		Course course = new Course();
		// image
		File imageFile = new File("C:/Users/Acer Nitro 5/Downloads/js.png");
		course.setImage(getByteArray(imageFile));
		course.setName("JavaScript");
		course.setDescription("These JavaScript lessions are for beginner.");
		course.setReview(0.0);

		File file = new File("C:/Users/Acer Nitro 5/Downloads/js.mp4");

		// video
		Video js1 = new Video("1 JavaScript Basic Introduction", getByteArray(file), course);
		Video js2 = new Video("2 JavaScript Basic First Program", getByteArray(file), course);
		Video js3 = new Video("3 JavaScript Basic Variable", getByteArray(file), course);
		Video js4 = new Video("4 JavaScript Basic DataType", getByteArray(file), course);

		course.getVideos().add(js1);
		course.getVideos().add(js2);
		course.getVideos().add(js3);
		course.getVideos().add(js4);

		List<Course> courses3 = new ArrayList<>();
		courses3.add(course);

		return courses3;

	}

	private List<Course> createCourseHtml() {

		Course course = new Course();
		// image
		File imageFile = new File("C:/Users/Acer Nitro 5/Downloads/html.png");
		course.setImage(getByteArray(imageFile));
		course.setName("HTML");
		course.setDescription("These HTML lessions are for beginner.");
		course.setReview(0.0);

		File file = new File("C:/Users/Acer Nitro 5/Downloads/programmingIntro.mp4");

		// video
		Video html1 = new Video("1 HTML Basic Introduction", getByteArray(file), course);
		Video html2 = new Video("2 HTML Basic Meta", getByteArray(file), course);
		Video html3 = new Video("3 HTML Basic Create Paragraph", getByteArray(file), course);
		Video html4 = new Video("4 HTML Basic Add Image", getByteArray(file), course);

		course.getVideos().add(html1);
		course.getVideos().add(html2);
		course.getVideos().add(html3);
		course.getVideos().add(html4);

		List<Course> courses4 = new ArrayList<>();
		courses4.add(course);

		return courses4;

	}

	private Byte[] getByteArray(File file) {
		byte[] videoData = null;
		try {
			byte[] buffer = new byte[4096];
			FileInputStream inputStream = new FileInputStream(file);
			ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			int length = 0;
			while ((length = bufferedInputStream.read(buffer)) != -1) {
				byteArrayStream.write(buffer, 0, length);
			}
			videoData = byteArrayStream.toByteArray();
			bufferedInputStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Byte[] video = new Byte[videoData.length];
		int i = 0;
		for (byte b : videoData) {
			video[i++] = b;
		}
		return video;
	}

	// account
	private Account createAccount() {
		Account account = new Account();
		account.setName("Admin");
		account.setEmail("admin@gmail.com");
		account.setPassword("123456");
		account.setRole("admin");
		account.setStatus("active");

		return account;
	}

	// project
	private List<Project> createProject() {

		Project project = new Project();
		// image
		File imageFile = new File("C:/Users/Acer Nitro 5/Downloads/javaOnlineShopPj.jpg");
		project.setImage(getByteArray(imageFile));
		project.setName("Java");
		project.setDescription("This Project is for beginner.");
		project.setReview(0.0);

		// video
		File file = new File("C:/Users/Acer Nitro 5/Downloads/programmingIntro.mp4");

		PjVideo java1 = new PjVideo("1 Online Shop Mini Project", getByteArray(file), project);

		// course - videos
		project.getVideos().add(java1);

		List<Project> project1 = new ArrayList<>();
		project1.add(project);

		return project1;
	}

	// project
	private List<Project> createProject1() {

		Project project = new Project();
		// image
		File imageFile = new File("C:/Users/Acer Nitro 5/Downloads/BlogWebsite.jpg");
		project.setImage(getByteArray(imageFile));
		project.setName("JavaScript");
		project.setDescription("These Project is  for beginner.");
		project.setReview(0.0);

		// video
		File file = new File("C:/Users/Acer Nitro 5/Downloads/programmingIntro.mp4");

		PjVideo java1 = new PjVideo("1 Blog Website Mini Project", getByteArray(file), project);

		// course - videos
		project.getVideos().add(java1);

		List<Project> project1 = new ArrayList<>();
		project1.add(project);

		return project1;
	}

}
