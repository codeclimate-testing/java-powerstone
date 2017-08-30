package org.powerstone;

import org.powerstone.sample.UserDAOTest;
import org.powerstone.sample.UserPagingControllerTest;
import org.powerstone.web.paging.PageModelTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.powerstone.*");
		//$JUnit-BEGIN$
		suite.addTestSuite(UserPagingControllerTest.class);
		suite.addTestSuite(UserDAOTest.class);
		suite.addTestSuite(PageModelTest.class);
		//$JUnit-END$
		return suite;
	}

}
