package com.scrum.website.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.scrum.domain.model.Story;
import com.scrum.domain.repository.StoryRepository;
import com.scrum.services.story.StorySerializer;
import com.scrum.website.exception.ResourceNotFoundException;

@Controller
public class StoryAndTaskController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private StorySerializer storySerializer;
	
	@RequestMapping(value="/stories", method = RequestMethod.GET)
	public ModelAndView stories() {
		LOG.info("Returning all stories");
		List<Story> allStories = storyRepository.getAllStories();
		return new ModelAndView("json/content", "json", storySerializer.toJson(allStories));
	}
	
	@RequestMapping(value="/story/{uniqueId}", method = RequestMethod.GET)
	public ModelAndView story(@PathVariable String uniqueId) {
		LOG.info("Returning story with unique id " + uniqueId);
		
		Story story = storyRepository.getStoryByUniqueId(uniqueId);
		
		if (story == null) {
			LOG.error("Story with unique id " + uniqueId + " not found");
			throw new ResourceNotFoundException("Story with unique id " + uniqueId + " not found");
		}
		
		return new ModelAndView("json/content", "json", storySerializer.toJson(story));
	}
	
	@RequestMapping(headers ={"Accept=application/json"}, value = "/story/json", method = RequestMethod.POST)
	public ModelAndView saveStory(@RequestBody String storyAsJson) {
		LOG.info("New story received " + storyAsJson);
		Story story = storySerializer.fromJson(storyAsJson);
		storyRepository.save(story);
		return new ModelAndView("json/success");
	}
}
