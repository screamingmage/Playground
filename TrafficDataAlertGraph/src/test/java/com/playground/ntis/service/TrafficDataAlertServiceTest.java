package com.playground.ntis.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.playground.ntis.model.TrafficDataAlert;
import com.playground.ntis.model.TrafficDataAlertGraph;
import com.playground.ntis.repository.NetworkLinkDao;

public class TrafficDataAlertServiceTest {
	
	private TrafficDataAlertService service;
	
	private List<TrafficDataAlert> alerts;
	private TrafficDataAlertGraph alertGraph;
	
	@Before
	public void before() {
		service = new TrafficDataAlertService(new NetworkLinkDao());
	}
	
	@Test
	public void aSingleRouteWithThreeAlertsIsSuccessfullyGraphed() {
		whenIHaveAListOfThreeTrafficDataAlerts();
		andATrafficDataAlertGraphIsProduced();
		thenThereShouldBeOneRoute();
	}
	
	@Test
	public void threeRoutesGraphedSuccessfully() {
		whenIHaveAListOfThirteenTrafficDataAlerts();
		andATrafficDataAlertGraphIsProduced();
		thenThereShouldBeThreeRoutes();
	}
	
	@Test
	public void routesWithOneAlertAreGraphedCorrectly() {
		whenIHaveAListOfSevenAlerts();
		andATrafficDataAlertGraphIsProduced();
		thenThereShouldBeSevenRoutes();
	}

	private void whenIHaveAListOfSevenAlerts() {
		alerts = new ArrayList<TrafficDataAlert>();
		
		alerts.add(createAlert(111L));
		alerts.add(createAlert(222L));
		alerts.add(createAlert(333L));
		alerts.add(createAlert(444L));
		alerts.add(createAlert(555L));
		alerts.add(createAlert(666L));
		alerts.add(createAlert(777L));
	}

	private void whenIHaveAListOfThirteenTrafficDataAlerts() {
		alerts = new ArrayList<TrafficDataAlert>();
		
		alerts.add(createAlert(141L));
		alerts.add(createAlert(142L));
		alerts.add(createAlert(143L));
		alerts.add(createAlert(144L));
		
		alerts.add(createAlert(277L));
		alerts.add(createAlert(278L));
		alerts.add(createAlert(279L));

		alerts.add(createAlert(719L));
		alerts.add(createAlert(720L));
		alerts.add(createAlert(721L));
		alerts.add(createAlert(722L));
		alerts.add(createAlert(723L));
		alerts.add(createAlert(724L));
	}

	private void whenIHaveAListOfThreeTrafficDataAlerts() {
		alerts = new ArrayList<TrafficDataAlert>();
		
		alerts.add(createAlert(104L));
		alerts.add(createAlert(105L));
		alerts.add(createAlert(106L));
	}

	private TrafficDataAlert createAlert(long linkId) {
		return new TrafficDataAlert(linkId, null, null, false);
	}
	
	private void andATrafficDataAlertGraphIsProduced() {
		alertGraph = service.createTrafficDataAlertGraph(alerts);
	}
	
	private void thenThereShouldBeOneRoute() {
		Map<TrafficDataAlert, Set<TrafficDataAlert>> graph = alertGraph.getGraph();
		assertEquals(1, graph.size());
		
		TrafficDataAlert headAlert = graph.keySet().iterator().next();
		assertEquals(106L, headAlert.getLinkId());
		
		Set<TrafficDataAlert> route = graph.get(headAlert);
		assertEquals(3, route.size());
	}
	
	private void thenThereShouldBeThreeRoutes() {
		Map<TrafficDataAlert, Set<TrafficDataAlert>> graph = alertGraph.getGraph();
		assertEquals(3, graph.size());
		
		Set<TrafficDataAlert> sortedSetOfAlertsOfHeadOfRoutes = createSortedSetOfGraphKeys();
		sortedSetOfAlertsOfHeadOfRoutes.addAll(graph.keySet());
		Iterator<TrafficDataAlert> it = sortedSetOfAlertsOfHeadOfRoutes.iterator();
		
		TrafficDataAlert headAlert = it.next();
		assertEquals(144L, headAlert.getLinkId());
		assertEquals(4, graph.get(headAlert).size());
		
		headAlert = it.next();
		assertEquals(279L, headAlert.getLinkId());
		assertEquals(3, graph.get(headAlert).size());
		
		headAlert = it.next();
		assertEquals(724L, headAlert.getLinkId());
		assertEquals(6, graph.get(headAlert).size());
	}
	
	private void thenThereShouldBeSevenRoutes() {
		Map<TrafficDataAlert, Set<TrafficDataAlert>> graph = alertGraph.getGraph();
		assertEquals(7, graph.size());
		
		Set<TrafficDataAlert> sortedSetOfAlertsOfHeadOfRoutes = createSortedSetOfGraphKeys();
		sortedSetOfAlertsOfHeadOfRoutes.addAll(graph.keySet());
		Iterator<TrafficDataAlert> it = sortedSetOfAlertsOfHeadOfRoutes.iterator();
		
		TrafficDataAlert headAlert = it.next();
		assertEquals(111L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());
		
		headAlert = it.next();
		assertEquals(222L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());
		
		headAlert = it.next();
		assertEquals(333L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());
		
		headAlert = it.next();
		assertEquals(444L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());

		headAlert = it.next();
		assertEquals(555L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());

		headAlert = it.next();
		assertEquals(666L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());

		headAlert = it.next();
		assertEquals(777L, headAlert.getLinkId());
		assertEquals(1, graph.get(headAlert).size());

	}
	
	private Set<TrafficDataAlert> createSortedSetOfGraphKeys() {
		Set<TrafficDataAlert> sortedSetOfAlertsOfHeadOfRoutes = new TreeSet<TrafficDataAlert>(new Comparator<TrafficDataAlert>(){
			
			public int compare(TrafficDataAlert a, TrafficDataAlert b){
                if (a.getLinkId() < b.getLinkId()) {
                	return -1;
                }
                
                if (a.getLinkId() > b.getLinkId()) {
                	return 1;
                }
                
                return 0;
			}
		});
		return sortedSetOfAlertsOfHeadOfRoutes;
	}
}
