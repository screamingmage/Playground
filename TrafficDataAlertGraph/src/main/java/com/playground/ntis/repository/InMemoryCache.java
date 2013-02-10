package com.playground.ntis.repository;

import java.util.HashMap;
import java.util.Map;

import com.playground.ntis.model.NetworkLink;

public class InMemoryCache {
	
	private Map<Long, NetworkLink> roadNetwork;
	
	public InMemoryCache() {
		roadNetwork = new HashMap<Long, NetworkLink>();
		
		for(int i=100; i<999; i++) {
			long linkId = i;
			roadNetwork.put(linkId, createLink(linkId));	
		}
	}
	
	public NetworkLink getNetworkLinkWithId(long linkId) {
		return roadNetwork.get(linkId);
	}
	
	private NetworkLink createLink(long linkId) {
		return new NetworkLink(linkId);
	}
}
