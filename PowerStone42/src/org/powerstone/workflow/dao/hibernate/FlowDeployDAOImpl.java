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

import org.powerstone.workflow.model.FlowDeploy;
import org.powerstone.workflow.model.FlowNodeBinding;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.dao.FlowDeployDAO;
import java.util.List;

public class FlowDeployDAOImpl
    extends HibernateDaoSupport
    implements FlowDeployDAO {
  private static Log log = LogFactory.getLog(FlowDeployDAOImpl.class);

  public FlowDeploy getFlowDeploy(Long flowDeployID) {
    return (FlowDeploy)
        getHibernateTemplate().load(FlowDeploy.class, flowDeployID);
  }

  public void saveFlowDeploy(FlowDeploy flowDeploy) {
    getHibernateTemplate().saveOrUpdate(flowDeploy);
    getHibernateTemplate().flush();
  }

  public void saveFlowNodeBinding(FlowNodeBinding flowNodeBinding) {
    getHibernateTemplate().saveOrUpdate(flowNodeBinding);
    getHibernateTemplate().flush();
  }

  public void removeFlowDeploy(Long flowDeployID) {
    FlowDeploy flowDeploy = this.getFlowDeploy(flowDeployID);
    getHibernateTemplate().delete(flowDeploy);
    getHibernateTemplate().flush();
  }

  public FlowNodeBinding getFlowNodeBinding(Long flowNodeBindingID) {
    return (FlowNodeBinding)
        getHibernateTemplate().load(FlowNodeBinding.class,
                                    flowNodeBindingID);
  }

  public List findFlowNodeBindingsByDriver(Long flowDriverID) {
    return getHibernateTemplate().findByNamedQuery("FlowNodeBindingsByDriver",
        flowDriverID);
  }

  public List findFlowNodeBindsByUserPerformer(Long userID) {
    return getHibernateTemplate().findByNamedQuery("FlowNodeBindsByUserPerformer",
        userID);
  }

  public List findFlowNodeBindsByRolePerformer(Long roleID) {
    return getHibernateTemplate().findByNamedQuery("FlowNodeBindsByRolePerformer",
        roleID);
  }
}
