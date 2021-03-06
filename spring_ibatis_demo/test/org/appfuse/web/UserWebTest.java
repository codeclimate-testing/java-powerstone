package org.appfuse.web;

import java.util.List;

import net.sourceforge.jwebunit.WebTestCase;

import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserWebTest extends WebTestCase {

    public UserWebTest(String name) {
        super(name);
        getTestContext().setBaseUrl("http://localhost:8080/equinox-ibatis");
        getTestContext().setResourceBundleName("messages");
        //getTestContext().setLocale(Locale.GERMAN);
        //getTestContext().getWebClient().setHeaderField("Accept-Language", "de");
    }

    public void testWelcomePage() {
        beginAt("/");
        assertTitleEqualsKey("index.title");
    }

    public void testAddUser() {
        beginAt("/editUser.html");
        assertTitleEqualsKey("userForm.title");
        setFormElement("firstName", "Spring");
        setFormElement("lastName", "User");
        submit("save");
        assertTitleEqualsKey("userList.title");
    }

    public void testListUsers() {
        beginAt("/users.html");

        // check that table is present
        assertTablePresent("userList");

        //check that a set of strings are present somewhere in table
        assertTextInTable("userList",
                new String[]{"Spring", "User"});
    }

    public void testEditUser() {
        beginAt("/editUser.html?id=" + getInsertedUserId());
        assertFormElementEquals("firstName", "Spring");
        submit("save");
        assertTitleEqualsKey("userList.title");
    }

    public void testDeleteUser() {
        beginAt("/editUser.html?id=" + getInsertedUserId());
        assertTitleEqualsKey("userForm.title");
        submit("delete");
        assertTitleEqualsKey("userList.title");
    }

    /**
     * Convenience method to get the id of the inserted user
     * Assumes last inserted user is "Spring User"
     */
    public String getInsertedUserId() {
        String[] paths = {"/WEB-INF/applicationContext*.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        List users = ((UserManager) ctx.getBean("userManager")).getUsers();
        // assumed that user inserted in testAddUser() is last user
        return "" + ((User) users.get(users.size() - 1)).getId();
    }
}
