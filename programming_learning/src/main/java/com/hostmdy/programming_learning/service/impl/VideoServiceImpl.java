package com.hostmdy.programming_learning.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.programming_learning.domain.Course;
import com.hostmdy.programming_learning.domain.Video;
import com.hostmdy.programming_learning.repository.CourseRepository;
import com.hostmdy.programming_learning.repository.VideoRepository;
import com.hostmdy.programming_learning.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	private final VideoRepository videoRepository;
	private final CourseRepository courseRepository;

	public VideoServiceImpl(VideoRepository videoRepository, CourseRepository courseRepository) {
		super();
		this.videoRepository = videoRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public Video saveVideo(Video video) {
		// TODO Auto-generated method stub
		return videoRepository.save(video);
	}

	@Override
	public Video createVideo(Video video, Long courseId) throws IOException {
		// TODO Auto-generated method stub
		Optional<Course> courseOpt = courseRepository.findById(courseId);

		if (courseOpt.isEmpty()) {
			throw new NullPointerException("courseId is not found!");
		}
		
		Course course = courseOpt.get();
		video.setCourse(courseOpt.get());
		course.getVideos().add(video);

		return saveVideo(video);
	}

	@Override
	public List<Video> getAllVideoByCourse(Long courseId) {
		// TODO Auto-generated method stub
		Optional<Course> courseOpt = courseRepository.findById(courseId);

		if (courseOpt.isEmpty()) {
			throw new NullPointerException("courseId is not found!");
		}

		return videoRepository.findByCourse(courseOpt.get());
	}

	@Override
	public Optional<Video> getVideoById(Long videoId) {
		// TODO Auto-generated method stub
		return videoRepository.findById(videoId);
	}

	@Override
	public void deleteVidoeById(Long videoId) {
		// TODO Auto-generated method stub
		videoRepository.deleteById(videoId);

	}

	@Override
	public Optional<Video> getVideoByName(String name) {
		// TODO Auto-generated method stub
		return videoRepository.findByName(name);
	}

}
