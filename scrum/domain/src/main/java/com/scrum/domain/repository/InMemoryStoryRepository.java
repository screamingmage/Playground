package com.scrum.domain.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.scrum.domain.builder.StoryBuilder;
import com.scrum.domain.builder.TaskBuilder;
import com.scrum.domain.model.Story;

@Service
public class InMemoryStoryRepository implements StoryRepository {
	
	private Map<String, Story> storiesMap;
	
	private int maxUniqueId;
	
	public InMemoryStoryRepository() {
		createSomeMockStories();
	}
	
	public void save(Story story) {
		maxUniqueId++;
		story.setUniqueId(String.valueOf(maxUniqueId));
		storiesMap.put(story.getUniqueId(), story);
	}
	
	public Story getStoryByUniqueId(String uniqueId) {
		return storiesMap.get(uniqueId);
	}
	
	public List<Story> getAllStories() {
		return new ArrayList<Story>(storiesMap.values());
	}
	
	private void createSomeMockStories() {
		storiesMap = new HashMap<String, Story>();
		
		for(int i=1; i<=10; i++) {
			Story story = new StoryBuilder()
				.uniqueId("" + i)
				.title("title " + i)
				.description("a new story chartered " + i)
				.build();
			
			story.addSubTask(new TaskBuilder()
				.uniqueId("TA" + i)
				.description("a new task chartered TA" + i)
				.timeRemainingInHours(4)
				.build());
			
			story.addSubTask(new TaskBuilder()
				.uniqueId("TB" + i)
				.description("a new task chartered TB" + i)
				.timeRemainingInHours(4)
				.build());
			
			storiesMap.put(story.getUniqueId(), story);
			
			maxUniqueId = i;
		}
	}
}
