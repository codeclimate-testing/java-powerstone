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

import org.powerstone.workflow.dao.FlowProcDAO;
import org.powerstone.workflow.model.FlowProc;
import org.powerstone.workflow.model.FlowProcRelativeData;
import org.powerstone.workflow.model.FlowProcTransaction;
import org.powerstone.workflow.model.FlowProcTransition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;
import org.powerstone.workflow.model.FlowTask;

public class FlowProcDAOImpl
    extends HibernateDaoSupport
    implements FlowProcDAO {
  private static Log log = LogFactory.getLog(FlowProcDAOImpl.class);

  public FlowProc getFlowProc(Long flowProcID) {
    return (FlowProc)
        getHibernateTemplate().load(FlowProc.class, flowProcID);
  }

  public void saveFlowProc(FlowProc flowProc) {
    getHibernateTemplate().saveOrUpdate(flowProc);
    getHibernateTemplate().flush();
  }

  public void removeFlowProc(Long flowProcID) {
    FlowProc flowProc = this.getFlowProc(flowProcID);
    getHibernateTemplate().delete(flowProc);
    getHibernateTemplate().flush();
  }

  public FlowProcRelativeData getFlowProcRelativeData(Long procRelativeDataID) {
    return (FlowProcRelativeData)
        getHibernateTemplate().load(FlowProcRelativeData.class,
                                    procRelativeDataID);
  }

  public FlowProcTransaction getFlowProcTransaction(Long procTransactionID) {
    return (FlowProcTransaction)
        getHibernateTemplate().load(FlowProcTransaction.class,
                                    procTransactionID);
  }

  public void saveFlowProcTransaction(FlowProcTransaction procTransaction) {
    getHibernateTemplate().saveOrUpdate(procTransaction);
    getHibernateTemplate().flush();
  }

  public FlowProcTransition getFlowProcTransition(Long procTransitionID) {
    return (FlowProcTransition)
        getHibernateTemplate().load(FlowProcTransition.class,
                                    procTransitionID);
  }

  /**
   * getActiveFlowProcsByDeploy
   *
   * @param flowDeployID Long
   * @return List
   */
  public List getActiveFlowProcsByDeploy(Long flowDeployID) {
    return getHibernateTemplate().findByNamedQuery("ActiveFlowProcsByDeploy",
        new Object[] {flowDeployID, FlowTask.TASK_STATE_FINISHED});
  }

  public void saveFlowProcTransition(FlowProcTransition procTransition) {
    getHibernateTemplate().saveOrUpdate(procTransition);
    getHibernateTemplate().flush();
  }
}
