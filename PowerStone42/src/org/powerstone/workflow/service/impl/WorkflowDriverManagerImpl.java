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
package org.powerstone.workflow.service.impl;

import org.powerstone.workflow.service.WorkflowDriverManager;
import org.powerstone.workflow.model.WFDriverInputParam;
import org.powerstone.workflow.model.WFDriverOutputParam;
import org.powerstone.workflow.model.WFDriverOutputParamEnume;
import java.util.List;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.dao.WorkflowDriverDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WorkflowDriverManagerImpl
    implements WorkflowDriverManager {
  private static Log log = LogFactory.getLog(WorkflowDriverManagerImpl.class);
  private WorkflowDriverDAO workflowDriverDAO;
  public void setWorkflowDriverDAO(WorkflowDriverDAO dao) {
    workflowDriverDAO = dao;
  }

  public List getAllWorkflowDrivers() {
    return workflowDriverDAO.getAllWorkflowDrivers();
  }

  public WorkflowDriver getWorkflowDriver(String flowDriverID) {
    return workflowDriverDAO.getWorkflowDriver(new Long(flowDriverID));
  }

  public WorkflowDriver saveWorkflowDriver(WorkflowDriver flowDriver) {
    workflowDriverDAO.saveWorkflowDriver(flowDriver);
    return flowDriver;
  }

  public WorkflowDriver updateWorkflowDriver(WorkflowDriver flowDriver) {
    WorkflowDriver result =
        getWorkflowDriver(flowDriver.getFlowDriverID().toString());
    result.setContextPath(flowDriver.getContextPath());
    result.setFlowDriverID(flowDriver.getFlowDriverID());
    result.setFlowDriverName(flowDriver.getFlowDriverName());
    result.setMemo(flowDriver.getMemo());
    result.setReadURL(flowDriver.getReadURL());
    result.setWriteURL(flowDriver.getWriteURL());
    workflowDriverDAO.saveWorkflowDriver(result);
    return result;
  }

  public void removeWorkflowDriver(String flowDriverID) {
    WorkflowDriver wd = this.getWorkflowDriver(flowDriverID);
//    wd.removeAllParams();
    workflowDriverDAO.removeWorkflowDriver(wd.getFlowDriverID());
  }

  public List getFlowDriversByContextPath(String contextPath) {
    return workflowDriverDAO.getFlowDriversByContextPath(contextPath);
  }

  public List findDriverByReadDO(String contextPath, String requestURL) {
    return workflowDriverDAO.findDriverByReadDO(contextPath, requestURL);
  }

  public List findDriverByWriteDO(String contextPath, String requestURL) {
    return workflowDriverDAO.findDriverByWriteDO(contextPath, requestURL);
  }

  public List getAllDriverContextPath() {
    return workflowDriverDAO.getAllDriverContextPath();
  }

  public WorkflowDriver addDriverInputParam(String driverID,
                                            WFDriverInputParam driverInputParam) {
    WorkflowDriver wd = this.getWorkflowDriver(driverID);
    wd.addInputParam(driverInputParam);
    workflowDriverDAO.saveWorkflowDriver(wd);
    return wd;
  }

  public WorkflowDriver addDriverOutputParam(String driverID,
                                             WFDriverOutputParam
                                             driverOutputParam) {
    WorkflowDriver wd = this.getWorkflowDriver(driverID);
    wd.addOutputParam(driverOutputParam);
    workflowDriverDAO.saveWorkflowDriver(wd);
    return wd;
  }

  public void removeDriverInputParam(String driverInputParamID) {
    WFDriverInputParam driverInputParam =
        workflowDriverDAO.getDriverInputParam(new Long(driverInputParamID));
    WorkflowDriver wd = driverInputParam.getWorkflowDriver();
    if (wd != null) {
      wd.removeInputParam(driverInputParam);
    }

    workflowDriverDAO.saveWorkflowDriver(wd);
  }

  public void removeDriverOutputParam(String driverOutputParamID) {
    WFDriverOutputParam driverOutputParam =
        workflowDriverDAO.getDriverOutputParam(new Long(driverOutputParamID));
    WorkflowDriver wd = driverOutputParam.getWorkflowDriver();
    if (wd != null) {
      wd.removeOutputParam(driverOutputParam);
    }

    workflowDriverDAO.saveWorkflowDriver(wd);
  }

  public void addDriverOutputParamEnume(String driverOutputParamID,
                                        WFDriverOutputParamEnume
                                        driverOutputParamEnume) {
    WFDriverOutputParam driverOutputParam =
        workflowDriverDAO.getDriverOutputParam(new Long(driverOutputParamID));
    driverOutputParam.addParamEnume(driverOutputParamEnume);
    workflowDriverDAO.saveDriverOutputParam(driverOutputParam);
  }

  public void removeDriverOutputParamEnume(String driverOutputParamEnumeID) {
    WFDriverOutputParamEnume driverOutputParamEnume =
        workflowDriverDAO.getDriverOutputParamEnume(new Long(
        driverOutputParamEnumeID));
    WFDriverOutputParam driverOutputParam =
        driverOutputParamEnume.getWfDriverOutputParam();

    if (driverOutputParam != null) {
      driverOutputParam.removeParamEnume(driverOutputParamEnume);
    }
    workflowDriverDAO.saveDriverOutputParam(driverOutputParam);
  }

  public WFDriverInputParam getDriverInputParam(String driverInputParamID) {
    return workflowDriverDAO.getDriverInputParam(new Long(driverInputParamID));
  }

  public WFDriverOutputParam getDriverOutputParam(String driverOutputParamID) {
    return workflowDriverDAO.getDriverOutputParam(new Long(driverOutputParamID));
  }

  public WFDriverOutputParamEnume getDriverOutputParamEnume(String
      driverOutputParamEnumeID) {
    return workflowDriverDAO.getDriverOutputParamEnume(new Long(
        driverOutputParamEnumeID));
  }
}
