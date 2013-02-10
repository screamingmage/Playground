package com.playground.ntis.model;

import org.joda.time.DateTime;

public class TrafficDataAlert {
	
	private long linkId;
	
	private DateTime startTime;
	private DateTime endTime;
	
	private boolean completed;
	
	private TrafficDataAlert upstream;
	private TrafficDataAlert downstream;

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

	public TrafficDataAlert getUpstream() {
		return upstream;
	}

	public void setUpstream(TrafficDataAlert upstream) {
		this.upstream = upstream;
	}

	public TrafficDataAlert getDownstream() {
		return downstream;
	}

	public void setDownstream(TrafficDataAlert downstream) {
		this.downstream = downstream;
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
