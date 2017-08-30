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
package org.powerstone.workflow.service;

import org.powerstone.workflow.dao.WorkflowDriverDAO;
import java.util.List;
import org.powerstone.workflow.model.WorkflowDriver;
import org.powerstone.workflow.model.WFDriverInputParam;
import org.powerstone.workflow.model.WFDriverOutputParam;
import org.powerstone.workflow.model.WFDriverOutputParamEnume;

public interface WorkflowDriverManager {
  public void setWorkflowDriverDAO(WorkflowDriverDAO dao);

  public List getAllWorkflowDrivers();

  public WorkflowDriver getWorkflowDriver(String flowDriverID);

  public WFDriverInputParam getDriverInputParam(String driverInputParamID);

  public WFDriverOutputParam getDriverOutputParam(String driverOutputParamID);

  public WFDriverOutputParamEnume getDriverOutputParamEnume(String
      driverOutputParamEnumeID);

  public WorkflowDriver saveWorkflowDriver(WorkflowDriver flowDriver);

  public WorkflowDriver updateWorkflowDriver(WorkflowDriver flowDriver);

  public void removeWorkflowDriver(String flowDriverID);

  public List getFlowDriversByContextPath(String contextPath);

  public List findDriverByReadDO(String contextPath,String requestURL);

  public List findDriverByWriteDO(String contextPath,String requestURL);

  public List getAllDriverContextPath();

  public WorkflowDriver addDriverInputParam(String driverID,
                                            WFDriverInputParam driverInputParam);

  public WorkflowDriver addDriverOutputParam(String driverID,
                                             WFDriverOutputParam
                                             driverOutputParam);

  public void removeDriverInputParam(String driverInputParamID);

  public void removeDriverOutputParam(String driverOutputParamID);

  public void addDriverOutputParamEnume(String driverOutputParamID,
                                        WFDriverOutputParamEnume
                                        driverOutputParamEnume);

  public void removeDriverOutputParamEnume(String driverOutputParamEnumeID);

}
