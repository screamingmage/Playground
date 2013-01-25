package com.scrum.domain.model;

import org.joda.time.Period;

public class Task {

	private String uniqueId;
	private String description;
	
	private Period timeSpent;
	private Period timeRemaining;
	
	public Task(String uniqueId, String description, int hoursRemaining) {
		this.uniqueId = uniqueId;
		this.description = description;
		
		if (timeRemaining == null) {
			timeRemaining = new Period();
		}
		
		this.timeRemaining = new Period().plusHours(hoursRemaining);
		this.timeSpent = new Period();
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public String getDescription() {
		return description;
	}

	public Period getTimeSpent() {
		return timeSpent;
	}

	public Period getTimeRemaining() {
		return timeRemaining;
	}

	public void addDaysSpent(int days) {
		timeSpent = timeSpent.plusDays(days);
	}
	
	public void addHoursSpent(int hours) {
		timeSpent = timeSpent.plusHours(hours);
	}
	
	public void addMinutesSpent(int minutes) {
		timeSpent = timeSpent.plusMinutes(minutes);
	}
}
