package com.scrum.services.gson;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.scrum.domain.builder.StoryBuilder;
import com.scrum.domain.builder.TaskBuilder;
import com.scrum.domain.model.Story;

public class GsonStorySerializerTest {
	
	public Story story;
	public List<Story> stories;
	public GsonStorySerializer serializer;
	public String storiesAsJson;
	
	@Before
	public void setup() {
		serializer = new GsonStorySerializer();
		stories = new ArrayList<Story>();
	}
	
	@Test
	public void singleStoryWithNoTasksDescribedCorrectlyInJsonFormat() {
		whenIHaveASingleStory();
		andItIsSerializedIntoJson();
		thenTheSingleStoryShouldBeDescribedIntoJsonCorrectly();
	}
	
	@Test
	public void twoStoriesWithNoTasksDescribedCorrectlyInJsonFormat() {
		whenIHaveTwoStories();
		andItIsSerializedIntoJson();
		thenTheTwoStoriesShouldBeDescribedIntoJsonCorrectly();
	}
	
	@Test
	public void singleStoryWithTwoTasksDescribedCorrectlyInJsonFormat() {
		whenIHaveASingleStoryWithTwoTasks();
		andItIsSerializedIntoJson();
		thenTheStoryAndTasksShouldBeDescribedIntoJsonCorrectly();
	}
	
	@Test
	public void jsonDeserializedIntoStoryCorrectly() {
		whenIHaveAStoryAsAJsonString();
		andItIsDeserializedIntoAStory();
		thenAStoryIsCorrectlyCreated();
	}
	
	private void whenIHaveAStoryAsAJsonString() {
		storiesAsJson = 
				"{" +
					"\"description\":\"a deserialized description\",\"title\":\"a deserialized title\"" +
				"}";
	}

	private void whenIHaveASingleStoryWithTwoTasks() {
	
		Story story = new StoryBuilder()
			.uniqueId("A12345")
			.description("A story")
			.title("title")
			.build();
		
		story.addSubTask(new TaskBuilder()
				.uniqueId("ABC")
				.description("First task")
				.timeRemainingInHours(6)
				.build());
		
		story.addSubTask(new TaskBuilder()
				.uniqueId("DEF")
				.description("Second task")
				.timeRemainingInHours(4)
				.build());
		
		stories.add(story);
	}

	private void whenIHaveASingleStory() {
		stories.add(new StoryBuilder().uniqueId("A12345").description("A story").build());
	}
	
	private void whenIHaveTwoStories() {
		stories.add(new StoryBuilder().uniqueId("A12345").description("A story").build());
		stories.add(new StoryBuilder().uniqueId("A67890").description("A second story").build());
	}
	
	private void andItIsSerializedIntoJson() {
		storiesAsJson = stories.size() == 1 ? serializer.toJson(stories.get(0)) : serializer.toJson(stories);
	}
	
	private void andItIsDeserializedIntoAStory() {
		story = serializer.fromJson(storiesAsJson);
	}
	
	private void thenAStoryIsCorrectlyCreated() {
		assertEquals("a deserialized title", story.getTitle());
		assertEquals("a deserialized description", story.getDescription());
	}
	
	private void thenTheSingleStoryShouldBeDescribedIntoJsonCorrectly() {
		assertEquals(
					"{" +
						"\"description\":\"A story\",\"uniqueId\":\"A12345\",\"subTasks\":[]" +
					"}", 
					storiesAsJson);
	}
	
	private void thenTheTwoStoriesShouldBeDescribedIntoJsonCorrectly() {
		assertEquals(
					"[" +
						"{\"description\":\"A story\",\"uniqueId\":\"A12345\",\"subTasks\":[]}," +
						"{\"description\":\"A second story\",\"uniqueId\":\"A67890\",\"subTasks\":[]}" +
					"]", 
					storiesAsJson);
	}
	
	private void thenTheStoryAndTasksShouldBeDescribedIntoJsonCorrectly() {
		assertEquals(
				"{" +
					"\"description\":\"A story\",\"title\":\"title\",\"uniqueId\":\"A12345\",\"subTasks\":" +
						"[" +
							"{\"uniqueId\":\"ABC\",\"description\":\"First task\",\"timeSpent\":\"PT0S\",\"timeRemaining\":\"PT6H\"}," +
							"{\"uniqueId\":\"DEF\",\"description\":\"Second task\",\"timeSpent\":\"PT0S\",\"timeRemaining\":\"PT4H\"}" +
						"]" +
				"}", 
				storiesAsJson);
	}
}
