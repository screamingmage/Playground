package com.playground.ntis.model;

public class NetworkLink {
	
	private Long linkId;

	public NetworkLink(Long linkId) {
		this.linkId = linkId;
	}

	public Long getLinkId() {
		return linkId;
	}

	@Override
	public String toString() {
		return "[linkId: " + linkId + "]";
	}
	
}
