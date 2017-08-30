package org.appfuse.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.dao.UserDAO;
import org.appfuse.model.User;
import org.appfuse.service.impl.UserManagerImpl;
import org.jmock.Mock;
import org.jmock.MockObjectTestCase;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;

public class UserManagerTest extends MockObjectTestCase {
    private final Log log = LogFactory.getLog(UserManagerTest.class);
    private UserManager mgr = new UserManagerImpl();
    private Mock mockDAO = null;

    protected void setUp() throws Exception {
        mockDAO = new Mock(UserDAO.class);
        mgr.setUserDAO((UserDAO) mockDAO.proxy());
    }

    public void testAddAndRemoveUser() throws Exception {
        User user = new User();
        user.setFirstName("Easter");
        user.setLastName("Bunny");

        // set expected behavior on dao
        mockDAO.expects(once()).method("saveUser").with(same(user));

        mgr.saveUser(user);

        // verify expectations
        mockDAO.verify();

        assertEquals(user.getFullName(), "Easter Bunny");

        if (log.isDebugEnabled()) {
            log.debug("removing user...");
        }

        mockDAO.expects(once()).method("removeUser")
                .with(eq(new Long(1)));

        mgr.removeUser("1");

        // verify expectations
        mockDAO.verify();

        try {
            // set expectations
            Throwable ex =
                    new ObjectRetrievalFailureException(User.class, "1");
            mockDAO.expects(once()).method("getUser")
                    .with(eq(new Long(1))).will(throwException(ex));

            user = mgr.getUser("1");

            // verify expectations
            mockDAO.verify();
            fail("User 'Easter Bunny' found in database");
        } catch (DataAccessException dae) {
            log.debug("Expected exception: " + dae.getMessage());
            assertNotNull(dae);
        }
    }
}
