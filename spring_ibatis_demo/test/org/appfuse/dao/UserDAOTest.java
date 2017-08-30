package org.appfuse.dao;

import org.appfuse.model.User;
import org.springframework.dao.DataAccessException;

public class UserDAOTest extends BaseDAOTestCase {
    private User user = null;
    private UserDAO dao = null;

    protected void setUp() throws Exception {
        super.setUp();
        dao = (UserDAO) ctx.getBean("userDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        dao = null;
    }

    public void testGetUsers() {
        user = new User();
        user.setFirstName("Rod");
        user.setLastName("Johnson");

        dao.saveUser(user);

        assertTrue(dao.getUsers().size() >= 1);
    }

    public void testSaveUser() throws Exception {
        user = new User();
        user.setFirstName("Rod");
        user.setLastName("Johnson");

        dao.saveUser(user);
        assertTrue("primary key assigned", user.getId() != null);

        assertTrue(user.getFirstName() != null);
    }

    public void testAddAndRemoveUser() throws Exception {
        user = new User();
        user.setFirstName("Bill");
        user.setLastName("Joy");

        dao.saveUser(user);

        assertTrue(user.getId() != null);
        assertTrue(user.getFirstName().equals("Bill"));

        if (log.isDebugEnabled()) {
            log.debug("removing user...");
        }

        dao.removeUser(user.getId());

        try {
            user = dao.getUser(user.getId());
            fail("User found in database");
        } catch (DataAccessException dae) {
            if (log.isDebugEnabled()) {
                log.debug("Expected exception: " + dae.getMessage());
            }

            assertTrue(dae != null);
        }
    }
}
