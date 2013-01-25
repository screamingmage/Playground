package com.scrum.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Period;

public class Story {
	
	private String description;
	private String title;
	private String uniqueId;
	
	private List<Task> subTasks;

	public Story(String uniqueId, String title, String description) {
		this.uniqueId = uniqueId;
		this.description = description;
		this.title = title;
		this.subTasks = new ArrayList<Task>();
	}

	public String getDescription() {
		return description;
	}
	
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getUniqueId() {
		return uniqueId;
	}

	public String getTitle() {
		return title;
	}

	public List<Task> getSubTasks() {
		return subTasks;
	}
	
	public void addSubTask(Task task) {
		this.subTasks.add(task);
	}

	public Period getTotalTimeSpent() {
		Period totalTimeSpent = new Period();
		for(Task task : subTasks) {
			totalTimeSpent = totalTimeSpent.plusDays(task.getTimeSpent().getDays());
			totalTimeSpent = totalTimeSpent.plusHours(task.getTimeSpent().getHours());
			totalTimeSpent = totalTimeSpent.plusMinutes(task.getTimeSpent().getMinutes());
		}
		
		return totalTimeSpent;
	}
}
