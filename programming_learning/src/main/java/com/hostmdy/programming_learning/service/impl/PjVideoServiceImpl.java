package com.hostmdy.programming_learning.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.programming_learning.domain.PjVideo;
import com.hostmdy.programming_learning.domain.Project;
import com.hostmdy.programming_learning.repository.PjVideoRepository;
import com.hostmdy.programming_learning.repository.ProjectRepository;
import com.hostmdy.programming_learning.service.PjVideoService;

@Service
public class PjVideoServiceImpl implements PjVideoService {

	private final PjVideoRepository pjVideoRepository;
	private final ProjectRepository projectRepository;

	public PjVideoServiceImpl(PjVideoRepository pjVideoRepository, ProjectRepository projectRepository) {
		super();
		this.pjVideoRepository = pjVideoRepository;
		this.projectRepository = projectRepository;
	}

	@Override
	public PjVideo savePjVideo(PjVideo video) {
		// TODO Auto-generated method stub
		return pjVideoRepository.save(video);
	}

	@Override
	public PjVideo createPjVideo(PjVideo video, Long projectId) throws IOException {
		// TODO Auto-generated method stub
		Optional<Project> projectOpt = projectRepository.findById(projectId);

		if (projectOpt.isEmpty()) {
			throw new NullPointerException("projectOpt is not found!");
		}
		Project project = projectOpt.get();
		video.setProject(projectOpt.get());
		project.getVideos().add(video);
		return savePjVideo(video);
	}

	@Override
	public List<PjVideo> getAllPjVideoByProject(Long projectId) {
		// TODO Auto-generated method stub
		Optional<Project> projectOpt = projectRepository.findById(projectId);

		if (projectOpt.isEmpty()) {
			throw new NullPointerException("projectOpt is not found!");
		}
		return pjVideoRepository.findByProject(projectOpt.get());
	}

	@Override
	public Optional<PjVideo> getPjVideoById(Long videoId) {
		// TODO Auto-generated method stub
		return pjVideoRepository.findById(videoId);
	}

	@Override
	public Optional<PjVideo> getPjVideoByName(String name) {
		// TODO Auto-generated method stub
		return pjVideoRepository.findByName(name);
	}

	@Override
	public void deletePjVidoeById(Long videoId) {
		// TODO Auto-generated method stub
		pjVideoRepository.deleteById(videoId);
	}

}
