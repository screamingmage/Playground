package com.playground.ntis.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NetworkLinkDaoTest {
		
	private NetworkLinkDao linkDao;
	
	@Before
	public void before() {
		linkDao = new NetworkLinkDao();
	}
	
	@Test
	public void testDownstream() {
		Long link = linkDao.getAdjacentDownstreamLink(104L);
		Assert.assertEquals(105L, link.longValue());
	}
	
	@Test
	public void testUpstream() {
		Long link = linkDao.getAdjacentUpstreamLink(104L);
		Assert.assertEquals(103L, link.longValue());
	}
}
