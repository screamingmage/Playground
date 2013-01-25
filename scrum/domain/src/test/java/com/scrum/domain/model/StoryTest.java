package com.scrum.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.scrum.domain.builder.StoryBuilder;
import com.scrum.domain.builder.TaskBuilder;

public class StoryTest {
	
	private static final int NINE_HOURS = 9;
	
	private Story story;
	
	@Test
	public void createStorySuccessfully() {
		iCreateAStory();
		thenTheStoryShouldBeSetCorrectly();
	}
	
	@Test
	public void totalTimeSpentCorrectlyUpdated() {
		iCreateAStory();
		thenIAddTwoSubTasks();
		thenTotalTimeSpentShouldBeNineHours();
	}

	private void thenTotalTimeSpentShouldBeNineHours() {
		assertEquals(NINE_HOURS, story.getTotalTimeSpent().getHours());
	}

	private void thenIAddTwoSubTasks() {
		story.addSubTask(createSubTask("123", "Task 1", 5));
		story.addSubTask(createSubTask("123", "Task 1", 4));
	}
	
	private void iCreateAStory() {
		story = new StoryBuilder()
			.uniqueId("B12345")
			.description("A new story")
			.title("story title")
			.build();
	}

	private void thenTheStoryShouldBeSetCorrectly() {
		assertEquals("B12345", story.getUniqueId());
		assertEquals("A new story", story.getDescription());
		assertEquals("story title", story.getTitle());
		assertEquals(0, story.getSubTasks().size());
	}

	private Task createSubTask(String uniqueId, String description, int hoursSpent) {
		Task task = new TaskBuilder()
					.uniqueId(uniqueId)
					.description(description)
					.build();
		
		task.addHoursSpent(hoursSpent);
		return task;
	}
}
