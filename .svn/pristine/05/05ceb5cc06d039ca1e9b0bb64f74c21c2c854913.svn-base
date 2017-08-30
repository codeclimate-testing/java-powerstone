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
package org.powerstone.workflow.dao.hibernate;

import java.util.List;
import org.powerstone.workflow.model.WorkflowMeta;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.powerstone.workflow.dao.WorkflowMetaDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: PowerStone</p>
 */

public class WorkflowMetaDAOImpl
    extends HibernateDaoSupport
    implements WorkflowMetaDAO {
  private static Log log = LogFactory.getLog(WorkflowMetaDAOImpl.class);

  public List getAllWorkflowMetas() {
    return getHibernateTemplate().findByNamedQuery("AllWorkflowMetas");
  }

  public WorkflowMeta getWorkflowMeta(Long flowMetaID) {
    return (WorkflowMeta) getHibernateTemplate().load(WorkflowMeta.class,
        flowMetaID);
  }

  public void saveWorkflowMeta(WorkflowMeta workflowMeta) {
    getHibernateTemplate().saveOrUpdate(workflowMeta);
    getHibernateTemplate().flush();
  }

  public void removeWorkflowMeta(Long flowMetaID) {
    Object obj =
        (WorkflowMeta) getHibernateTemplate().load(WorkflowMeta.class,
        flowMetaID);
  getHibernateTemplate().delete(obj);
    getHibernateTemplate().flush();
  }

  public WorkflowMeta getWorkflowMetaByProcess(String flowProcessID) {
    List result =
        getHibernateTemplate().findByNamedQuery("WorkflowMetaByProcess",
                                                new String[] {flowProcessID}
                                                );
    if (result != null && result.size() > 0) {
      return (WorkflowMeta) result.get(0);
    }
    return null;
  }

  /**
   * getWorkflowMetasNoBusinessType
   * @return List
   */
  public List getWorkflowMetasNoBusinessType() {
    List result =
        getHibernateTemplate().findByNamedQuery("WorkflowMetasNoBusinessType");
    return result;
  }

}
