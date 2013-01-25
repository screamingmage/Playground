package com.scrum.domain.builder;

import com.scrum.domain.model.Task;

public class TaskBuilder {
	
	private int hoursRemaining;

	private String uniqueId;
	private String description;
	
	public TaskBuilder() {
		uniqueId = null;
		description = null;
		hoursRemaining = 0;
	}
	
	public TaskBuilder uniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}
	
	public TaskBuilder description(String desc) {
		this.description = desc;
		return this;
	}
	
	public TaskBuilder timeRemainingInHours(int hours) {
		this.hoursRemaining = hours;
		return this;
	}
	
	public Task build() {
		return new Task(uniqueId, description, hoursRemaining);
	}
}
