package com.playground.ntis.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playground.ntis.model.NetworkLink;

public class NetworkLinkDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(NetworkLinkDao.class);
	
	private InMemoryCache cache;
	
	public NetworkLinkDao() {
		cache = new InMemoryCache();
	}
	
	public Long getAdjacentUpstreamLink(long linkId) {
		LOG.info("Find upstream link for " + linkId);
		long adjacentLinkId = linkId - 1;
		
		LOG.info("Looking for network link with id " + adjacentLinkId);
		NetworkLink link = cache.getNetworkLinkWithId(adjacentLinkId);
		
		if (link == null) {
			return null;
		}
		
		return link.getLinkId(); 
	}
	
	public Long getAdjacentDownstreamLink(long linkId) {
		LOG.info("Find downstream link for " + linkId);
		long downstreamLinkId = linkId + 1;
		
		LOG.info("Looking for network link with id " + downstreamLinkId);
		NetworkLink link = cache.getNetworkLinkWithId(downstreamLinkId);
		
		if (link == null) {
			return null;
		}
		
		return link.getLinkId();
	}
}
