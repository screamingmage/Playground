package com.playground.ntis.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrafficDataAlertGraph {
	
	private final Map<TrafficDataAlert, Set<TrafficDataAlert>> routeGraph;
	
	public TrafficDataAlertGraph(List<TrafficDataAlert> alerts) {
		this.routeGraph = new HashMap<TrafficDataAlert, Set<TrafficDataAlert>>();
		
		addAlertsWhichAreAtTheHeadOfRoutes(alerts);
		addRoutesForAlerts();
	}
	
	public Map<TrafficDataAlert, Set<TrafficDataAlert>> getGraph() {
		return routeGraph;
	}
	
	public Iterator<TrafficDataAlert> iterator() {
        return routeGraph.keySet().iterator();
    }
	
	private void addAlertsWhichAreAtTheHeadOfRoutes(List<TrafficDataAlert> alerts) {
		clearGraph();
		
		for (TrafficDataAlert alert : alerts) {
			if (isAlertAtHeadOfARoute(alert)) {
				routeGraph.put(alert, new HashSet<TrafficDataAlert>());
			}
		}
	}
	
	private void addRoutesForAlerts() {
		for (TrafficDataAlert alert : routeGraph.keySet()) {
			Set<TrafficDataAlert> route = new HashSet<TrafficDataAlert>();
			populateRouteWithAlertHead(route, alert);
			routeGraph.put(alert, route);
		}
	}
	
	private void populateRouteWithAlertHead(Set<TrafficDataAlert> route, TrafficDataAlert alert) {
		if (alert == null) {
			return;
		}
		
		route.add(alert);
		
		for (TrafficDataAlert upstreamAlert : alert.getUpstreamAlerts()) {
			populateRouteWithAlertHead(route, upstreamAlert);
		}
	}

	private boolean isAlertAtHeadOfARoute(TrafficDataAlert alert) {
		return alert.getDownstreamAlerts().size() == 0;
	}
	
	private void clearGraph() {
		routeGraph.clear();
	}
}
