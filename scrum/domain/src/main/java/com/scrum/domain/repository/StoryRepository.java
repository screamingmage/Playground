package com.scrum.domain.repository;

import java.util.List;

import com.scrum.domain.model.Story;

public interface StoryRepository {
	
	void save(Story story);
	
	Story getStoryByUniqueId(String uniqueId);
	
	List<Story> getAllStories();
}
