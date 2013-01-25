package com.scrum.services.gson;

import java.util.List;

import org.joda.time.Period;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrum.domain.model.Story;
import com.scrum.services.gson.adapter.PeriodSerializer;
import com.scrum.services.story.StorySerializer;

@Service
public class GsonStorySerializer implements StorySerializer {

	public String toJson(Story story) {
		GsonBuilder builder = createBuilderAndRegisterAdapters();
		Gson gson = builder.create();
		return gson.toJson(story);
	}
	
	public String toJson(List<Story> stories) {
		GsonBuilder builder = createBuilderAndRegisterAdapters();
		Gson gson = builder.create();
		return gson.toJson(stories);
	}
	
	public Story fromJson(String json) {
		GsonBuilder builder = createBuilderAndRegisterAdapters();
		Gson gson = builder.create();
		return gson.fromJson(json, Story.class);
	}
	
	private GsonBuilder createBuilderAndRegisterAdapters() {
		GsonBuilder builder = new GsonBuilder(); 
		registerAdapters(builder);
		return builder;
	}

	private void registerAdapters(GsonBuilder gson) {
		gson.registerTypeAdapter(Period.class, new PeriodSerializer());
	}
}
