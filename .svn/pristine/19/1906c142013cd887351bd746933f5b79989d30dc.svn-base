package org.appfuse.dao;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Base class for DAO TestCases.
 *
 * @author Matt Raible
 */
public class BaseDAOTestCase extends TestCase {
    protected final Log log = LogFactory.getLog(getClass());
    protected ApplicationContext ctx = null;

    public BaseDAOTestCase() {
        String[] paths = {"/WEB-INF/applicationContext*.xml"};
        ctx = new ClassPathXmlApplicationContext(paths);
    }
}
