/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone;

import java.util.ArrayList;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class AbstractSpringTestCase extends
		AbstractTransactionalDataSourceSpringContextTests {
	protected static Log log = LogFactory.getLog(AbstractSpringTestCase.class);

	protected ArrayList contexts = null;

	public AbstractSpringTestCase() {
		// super.setDefaultRollback(false);
		contexts = new ArrayList();
		contexts.add("/WEB-INF/ps_hibernate_context.xml");
		contexts.add("/WEB-INF/workflow_ca_context.xml");
		contexts.add("/WEB-INF/workflow_service_context.xml");
		contexts.add("/WEB-INF/workflow_engine_context.xml");
	}

	public String[] getConfigLocations() {
		String[] tmp = new String[contexts.size()];
		return (String[]) contexts.toArray(tmp);
		// return new String[]{"classpath*:*context.xml"};
	}

	protected void flushSession() {
		SessionFactory sessionFactory = (SessionFactory) applicationContext
				.getBean("sessionFactory");
		sessionFactory.getCurrentSession().flush();
	}
}
