package com.playground.ntis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.playground.ntis.model.TrafficDataAlert;
import com.playground.ntis.model.TrafficDataAlertGraph;
import com.playground.ntis.repository.NetworkLinkDao;

public class TrafficDataAlertService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TrafficDataAlertService.class);
	
	private NetworkLinkDao linkDao;
	
	public TrafficDataAlertService(NetworkLinkDao linkDao) {
		this.linkDao = linkDao;
	}
	
	public TrafficDataAlertGraph createTrafficDataAlertGraph(List<TrafficDataAlert> alerts){
		populateTrafficDataAlertsWithAdjacentLinks(alerts);
		return new TrafficDataAlertGraph(alerts);
	}
	
	private void populateTrafficDataAlertsWithAdjacentLinks(List<TrafficDataAlert> alerts) {
		Map<Long, TrafficDataAlert> linksToAlerts = createMapOfLinksToAlerts(alerts);
		
		for (TrafficDataAlert alert : alerts) {
			setupDownstreamLink(linksToAlerts, alert);
			setupUpstreamLink(linksToAlerts, alert);
		}
	}
	
	private Map<Long, TrafficDataAlert> createMapOfLinksToAlerts(
			List<TrafficDataAlert> alerts) {

		Map<Long, TrafficDataAlert> linksToAlerts = new HashMap<Long, TrafficDataAlert>();
		
		for (TrafficDataAlert alert : alerts) {
			linksToAlerts.put(alert.getLinkId(), alert);
		}
		
		return linksToAlerts;
	}

	private void setupUpstreamLink(Map<Long, TrafficDataAlert> linksToAlerts,
			TrafficDataAlert alert) {
		LOG.info("Finding upstream link for " + alert.getLinkId());
		
		Long upstreamLink = linkDao.getAdjacentUpstreamLink(alert.getLinkId());
		if (upstreamLink != null) {
			LOG.info("Found " + upstreamLink);
			alert.setUpstream(linksToAlerts.get(upstreamLink));
		}
	}
	
	private void setupDownstreamLink(Map<Long, TrafficDataAlert> linksToAlerts,
			TrafficDataAlert alert) {
		
		LOG.info("Finding downstream link for " + alert.getLinkId());
		Long downstreamLink = linkDao.getAdjacentDownstreamLink(alert.getLinkId());
		if (downstreamLink != null) {
			LOG.info("Found " + downstreamLink);
			alert.setDownstream(linksToAlerts.get(downstreamLink));
		}
	}
}
