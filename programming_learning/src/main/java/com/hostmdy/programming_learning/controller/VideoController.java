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

import com.hostmdy.programming_learning.domain.Course;
import com.hostmdy.programming_learning.domain.Video;
import com.hostmdy.programming_learning.service.VideoService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/video")
public class VideoController {

	private final VideoService videoService;

	public VideoController(VideoService videoService) {
		super();
		this.videoService = videoService;
	}

	@GetMapping("/{videoId}/show")
	public void showVideo(@PathVariable Long videoId, HttpServletResponse response) {
		Optional<Video> videoOpt = videoService.getVideoById(videoId);

		if (videoOpt.isEmpty()) {
			throw new RuntimeException("course is not found!");
		}

		Video video = videoOpt.get();

		byte[] videoBytes = new byte[video.getVideoData().length];

		int i = 0;
		for (final byte b : video.getVideoData()) {
			videoBytes[i++] = b;
		}

		InputStream ls = new ByteArrayInputStream(videoBytes);

		response.setContentType("video/mp4");

		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/new/{courseId}")
	public String createVideoForm(@PathVariable Long courseId, Model model) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("video", new Video());
		return "video/video-form";
	}

	@PostMapping("/new")
	public String createVideo(@RequestParam Long courseId, @ModelAttribute Video video,
			@RequestParam MultipartFile videofile) throws IOException {
		if (!videofile.isEmpty()) {
			Byte[] videoBytes = new Byte[videofile.getBytes().length];
			int i = 0;
			for (final Byte b : videofile.getBytes()) {
				videoBytes[i++] = b;
			}
			video.setVideoData(videoBytes);
		}
		if (video.getId() != null) {
			Optional<Video> tempVideoOpt = videoService.getVideoById(video.getId());
			Video tempVideo = tempVideoOpt.get();
			if (videofile.isEmpty()) {
				video.setVideoData(tempVideo.getVideoData());
			}
			video.setCreateAt(tempVideo.getCreateAt());
		}
		Video createdVideo = videoService.createVideo(video, courseId);
		return "redirect:/course/" + courseId + "/show";
	}

	@GetMapping("/video/{videoId}/show")
	public void showVideoOfVideo(@PathVariable Long videoId, HttpServletResponse response) {
		Optional<Video> videoOpt = videoService.getVideoById(videoId);

		if (videoOpt.isEmpty()) {
			throw new RuntimeException("recipe is not found!");
		}

		Video video = videoOpt.get();

		byte[] videoBytes = new byte[video.getVideoData().length];

		int i = 0;
		for (final byte b : video.getVideoData()) {
			videoBytes[i++] = b;
		}

		InputStream ls = new ByteArrayInputStream(videoBytes);
		response.setContentType("video/mp4");

		try {
			IOUtils.copy(ls, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/{videoId}/update")
	public String updateVideoForm(@PathVariable Long videoId, Model model) {
		Optional<Video> videoOpt = videoService.getVideoById(videoId);
		if (videoOpt.isEmpty()) {
			throw new NullPointerException("video is not found!");
		}
		Video video = videoOpt.get();
		model.addAttribute("courseId", video.getCourse().getId());
		model.addAttribute("video", video);
		return "video/video-form";
	}

	@GetMapping("/{videoId}/delete/{courseId}")
	public String deleteVideo(@PathVariable Long videoId, @PathVariable Long courseId) {
		videoService.deleteVidoeById(videoId);
		return "redirect:/course/" + courseId + "/show";
	}

}
