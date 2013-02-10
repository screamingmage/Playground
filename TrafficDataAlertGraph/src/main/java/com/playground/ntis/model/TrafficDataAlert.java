package com.playground.ntis.model;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class TrafficDataAlert {
	
	private long linkId;
	
	private DateTime startTime;
	private DateTime endTime;
	
	private boolean completed;
	
	private List<TrafficDataAlert> upstreamAlerts;
	private List<TrafficDataAlert> downstreamAlerts;

	public TrafficDataAlert(
			final long linkId, 
			final DateTime startTime, 
			final DateTime endTime,
			final boolean completed) {
	
		super();
		this.linkId = linkId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.completed = completed;
		
		upstreamAlerts = new ArrayList<TrafficDataAlert>();
		downstreamAlerts = new ArrayList<TrafficDataAlert>();
	}

	public long getLinkId() {
		return linkId;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public boolean isCompleted() {
		return completed;
	}
	
	public void addUpstreamAlert(TrafficDataAlert alert) {
		this.upstreamAlerts.add(alert);
	}
	
	public List<TrafficDataAlert> getUpstreamAlerts() {
		return upstreamAlerts;
	}
	
	public void addDownstreamAlert(TrafficDataAlert alert) {
		this.downstreamAlerts.add(alert);
	}
	
	public List<TrafficDataAlert> getDownstreamAlerts() {
		return downstreamAlerts;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof TrafficDataAlert)) {
			return false;
		}
		
		return this.linkId == ((TrafficDataAlert) obj).linkId;
	}

	@Override
	public String toString() {
		return "[linkId: " + linkId + "]";
	}
}
