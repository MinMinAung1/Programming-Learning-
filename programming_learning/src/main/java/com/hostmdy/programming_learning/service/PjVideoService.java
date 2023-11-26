package com.hostmdy.programming_learning.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.hostmdy.programming_learning.domain.PjVideo;

public interface PjVideoService {
	
PjVideo savePjVideo(PjVideo video);
	
	PjVideo createPjVideo(PjVideo video,Long projectId) throws IOException;
	
	List<PjVideo> getAllPjVideoByProject(Long projectId);
	
	Optional<PjVideo> getPjVideoById(Long videoId);
	
	Optional<PjVideo> getPjVideoByName(String name);
	
	void deletePjVidoeById(Long videoId);

}
