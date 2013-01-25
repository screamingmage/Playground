package com.scrum.services.story;

import java.util.List;

import com.scrum.domain.model.Story;

public interface StorySerializer {
	
	String toJson(Story story);
	
	String toJson(List<Story> stories);
	
	Story fromJson(String json);
}
