package com.hostmdy.programming_learning.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.hostmdy.programming_learning.domain.Video;

public interface VideoService {
	
	Video saveVideo(Video video);
	
	Video createVideo(Video video,Long courseId) throws IOException;
	
	List<Video> getAllVideoByCourse(Long courseId);
	
	Optional<Video> getVideoById(Long videoId);
	
	Optional<Video> getVideoByName(String name);
	
	void deleteVidoeById(Long videoId);

}
