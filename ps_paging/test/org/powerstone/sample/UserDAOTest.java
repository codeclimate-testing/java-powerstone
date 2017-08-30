package org.powerstone.sample;

import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	UserDAO dao = new UserDAO();

	HashMap map;

	protected void setUp() throws Exception {
		super.setUp();
		map = new HashMap();
		map.put("firstName", "a_");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'org.powerstone.sample.UserDAO.findUsers(Map, int, int)'
	 */
	public void testFindUsers() {
		List users=dao.findUsers(map, 0, 200);
		Assert.assertEquals("findUsers(map, 0, 200)",108,users.size());
		
		users=dao.findUsers(map, 0, 30);
		Assert.assertEquals("findUsers(map, 0, 30)",30,users.size());
		
		users=dao.findUsers(map, 12, 30);
		Assert.assertEquals("findUsers(map,12, 30)",30,users.size());
		
		map.put("firstName", "x");
		users=dao.findUsers(map, 12, 30);
		Assert.assertEquals("findUsers(map,0, 200)",0,users.size());
	}

	/*
	 * Test method for 'org.powerstone.sample.UserDAO.countUsers(Map)'
	 */
	public void testCountUsers() {
		int users=dao.countUsers(map);
		Assert.assertEquals("dao.countUsers(map)",108,users);
	}

}
