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

import org.powerstone.workflow.dao.WorkflowDriverDAO;
import java.util.List;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.model.WFDriverOutputParam;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.model.WFDriverInputParam;
import org.powerstone.workflow.model.WFDriverOutputParamEnume;

public class WorkflowDriverDAOImpl
    extends HibernateDaoSupport
    implements WorkflowDriverDAO {
  private static Log log = LogFactory.getLog(WorkflowDriverDAOImpl.class);

  public List getAllWorkflowDrivers() {
    return getHibernateTemplate().findByNamedQuery("AllWorkflowDrivers");
  }

  public WorkflowDriver getWorkflowDriver(Long flowDriverID) {
    return (WorkflowDriver) getHibernateTemplate().load(WorkflowDriver.class,
        flowDriverID);
  }

  public void saveWorkflowDriver(WorkflowDriver flowDriver) {
    getHibernateTemplate().saveOrUpdate(flowDriver);
    getHibernateTemplate().flush();
  }

  public void removeWorkflowDriver(Long flowDriverID) {
    Object flowDriver =getWorkflowDriver(flowDriverID);
    getHibernateTemplate().delete(flowDriver);
    getHibernateTemplate().flush();
  }

  public List getFlowDriversByContextPath(String contextPath) {
    return getHibernateTemplate().findByNamedQuery("FlowDriversByContextPath",
        contextPath);
  }

  public List findDriverByReadDO(String contextPath,String requestURL) {
    return getHibernateTemplate().findByNamedQuery("DriverByReadDO",
    new Object[]{contextPath,requestURL}
        );
  }

  public List findDriverByWriteDO(String contextPath,String requestURL) {
    return getHibernateTemplate().findByNamedQuery("DriverByWriteDO",
        new Object[]{contextPath,requestURL});
  }

  public List getAllDriverContextPath() {
    return getHibernateTemplate().findByNamedQuery("AllDriverContextPath");
  }

  public WFDriverOutputParam getDriverOutputParam(Long driverOutputParamID) {
    return (WFDriverOutputParam)
        getHibernateTemplate().load(WFDriverOutputParam.class,
                                    driverOutputParamID);
  }

  public WFDriverInputParam getDriverInputParam(Long driverInputParamID) {
    return (WFDriverInputParam)
        getHibernateTemplate().load(WFDriverInputParam.class,
                                    driverInputParamID);
  }

  public WFDriverOutputParamEnume getDriverOutputParamEnume(Long
      driverOutputParamEnumeID) {
    return (WFDriverOutputParamEnume)
        getHibernateTemplate().load(WFDriverOutputParamEnume.class,
                                    driverOutputParamEnumeID);
  }

  public void saveDriverOutputParam(WFDriverOutputParam driverOutputParam) {
    getHibernateTemplate().saveOrUpdate(driverOutputParam);
    getHibernateTemplate().flush();
  }
}
