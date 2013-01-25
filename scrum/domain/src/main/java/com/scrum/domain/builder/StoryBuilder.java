package com.scrum.domain.builder;

import com.scrum.domain.model.Story;

public class StoryBuilder {
	
	private String description;
	private String title;
	private String uniqueId;
	
	public StoryBuilder() {
		uniqueId = null;
		description = null;
	}
	
	public StoryBuilder uniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}
	
	public StoryBuilder description(String desc) {
		this.description = desc;
		return this;
	}
	
	public StoryBuilder title(String title) {
		this.title = title;
		return this;
	}
	
	public Story build() {
		return new Story(uniqueId, title, description);
	}
}
