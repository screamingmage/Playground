package com.scrum.domain.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.scrum.domain.builder.TaskBuilder;

public class TaskTest {
	
	private Task task;
	
	@Test
	public void createTaskSuccessfully() {
		createATaskWithOneDayRemaning();
		thenTaskShouldBePopulatedCorrectly();
	}
	
	@Test
	public void updateTimeSpentSuccessfully() {
		createATaskWithOneDayRemaning();
		whenIUpdateTheTimeSpentOnATask();
		thenTheTimeSpentShouldBeCorrect();
	}
	
	private void thenTheTimeSpentShouldBeCorrect() {
		assertEquals(1, task.getTimeSpent().getDays());
		assertEquals(2, task.getTimeSpent().getHours());
		assertEquals(30, task.getTimeSpent().getMinutes());
	}

	private void whenIUpdateTheTimeSpentOnATask() {
		task.addDaysSpent(1);
		task.addHoursSpent(2);
		task.addMinutesSpent(30);
	}

	private void thenTaskShouldBePopulatedCorrectly() {
		assertEquals("A12345", task.getUniqueId());
		assertEquals("A new task", task.getDescription());
		assertEquals(7, task.getTimeRemaining().getHours());
	}

	private void createATaskWithOneDayRemaning() {
		task = new TaskBuilder()
				.uniqueId("A12345")
				.description("A new task")
				.timeRemainingInHours(7)
				.build();
	}
}
