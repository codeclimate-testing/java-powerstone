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
package org.powerstone.workflow.dao;

import org.powerstone.workflow.model.WorkflowDriver;
import java.util.List;
import org.powerstone.workflow.model.WFDriverOutputParam;
import org.powerstone.workflow.model.WFDriverInputParam;
import org.powerstone.workflow.model.WFDriverOutputParamEnume;

public interface WorkflowDriverDAO {
  public List getAllWorkflowDrivers();

  public WorkflowDriver getWorkflowDriver(Long flowDriverID);

  public void saveWorkflowDriver(WorkflowDriver flowDriver);

  public void removeWorkflowDriver(Long flowDriverID);

  public List getFlowDriversByContextPath(String contextPath);

  public List findDriverByReadDO(String contextPath,String requestURL);

  public List findDriverByWriteDO(String contextPath,String requestURL);

  public List getAllDriverContextPath();

  public WFDriverOutputParam getDriverOutputParam(Long driverOutputParamID);

  public WFDriverInputParam getDriverInputParam(Long driverInputParamID);

  public WFDriverOutputParamEnume getDriverOutputParamEnume(Long driverOutputParamEnumeID);

  public void saveDriverOutputParam(WFDriverOutputParam driverOutputParam);

}
