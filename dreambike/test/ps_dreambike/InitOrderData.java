package ps_dreambike;

import junit.framework.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public class InitOrderData extends TestCase {
  protected static Log log = LogFactory.getLog(InitOrderData.class);
  protected ApplicationContext ctx = null;
  protected SessionFactory sessionFactory = null;

  public InitOrderData() {
    String[] paths = {
        "/WEB-INF/dreambike_context.xml"};
    ctx = new ClassPathXmlApplicationContext(paths);
  }

  protected void setUp() throws Exception {
    super.setUp();
    SessionFactory sessionFactory = (SessionFactory) ctx.getBean(
        "sessionFactory");
    Session s = sessionFactory.openSession();
    TransactionSynchronizationManager.bindResource(sessionFactory,
        new SessionHolder(s));
  }

  protected void tearDown() throws Exception {
    super.tearDown();
    SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.
        getResource(sessionFactory);
    Session s = null;
    if (holder != null) {
      s = holder.getSession();
      s.flush();
      TransactionSynchronizationManager.unbindResource(sessionFactory);
      SessionFactoryUtils.closeSessionIfNecessary(s, sessionFactory);
    }
  }

  public void testInit() {
    DreambikeManager dm = (DreambikeManager) ctx.getBean("dreambikeManager");
    for (int i = 0; i < 100; i++) {
      BikeOrder bo = new BikeOrder();
      bo.setCustomeEmail("CustomeEmail" + i);
      bo.setCustomeID("CustomeID" + i);
      bo.setProductID("ProductID" + i);
      dm.order(bo);
    }
  }
}
