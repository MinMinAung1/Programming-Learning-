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

import com.hostmdy.programming_learning.domain.PjVideo;
import com.hostmdy.programming_learning.service.PjVideoService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pjvideo")
public class PjVideoController {

	private final PjVideoService pjVideoService;

	public PjVideoController(PjVideoService pjVideoService) {
		super();
		this.pjVideoService = pjVideoService;
	}

	@GetMapping("/{videoId}/show")
	public void showVideo(@PathVariable Long videoId, HttpServletResponse response) {
		Optional<PjVideo> projectVideoOpt = pjVideoService.getPjVideoById(videoId);

		if (projectVideoOpt.isEmpty()) {
			throw new RuntimeException("project is not found!");
		}

		PjVideo video = projectVideoOpt.get();

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

	@GetMapping("/new/{projectId}")
	public String createPjVideoForm(@PathVariable Long projectId, Model model) {
		model.addAttribute("projectId", projectId);
		model.addAttribute("video", new PjVideo());
		return "project/add-pjvideo";
	}

	@PostMapping("/new")
	public String createProjectVideo(@RequestParam Long projectId, @ModelAttribute PjVideo video,
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
			Optional<PjVideo> tempVideoOpt = pjVideoService.getPjVideoById(video.getId());
			PjVideo tempVideo = tempVideoOpt.get();
			if (videofile.isEmpty()) {
				video.setVideoData(tempVideo.getVideoData());
			}
			video.setCreateAt(tempVideo.getCreateAt());
		}
		PjVideo createdVideo = pjVideoService.createPjVideo(video, projectId);
		return "redirect:/project/" + projectId + "/show";
	}

	@GetMapping("/{videoId}/update")
	public String updateProjectVideoForm(@PathVariable Long videoId, Model model) {
		Optional<PjVideo> videoOpt = pjVideoService.getPjVideoById(videoId);
		if (videoOpt.isEmpty()) {
			throw new NullPointerException("video is not found!");
		}
		PjVideo video = videoOpt.get();
		model.addAttribute("projectId", video.getProject().getId());
		model.addAttribute("video", video);
		return "project/add-pjvideo";
	}

	@GetMapping("/{videoId}/delete/{projectId}")
	public String deleteProjectVideo(@PathVariable Long videoId, @PathVariable Long projectId) {
		pjVideoService.deletePjVidoeById(videoId);
		return "redirect:/project/" + projectId + "/show";
	}

}
