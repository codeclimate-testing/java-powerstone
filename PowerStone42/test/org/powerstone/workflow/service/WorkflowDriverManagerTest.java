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

import org.powerstone.workflow.model.*;
import java.util.*;
import org.apache.commons.logging.LogFactory;
import org.powerstone.AbstractSpringTestCase;

public class WorkflowDriverManagerTest
    extends AbstractSpringTestCase {
  private WorkflowDriverManager workflowDriverManager = null;
  private WorkflowDriver wd;
  WFDriverOutputParam wdOutParam;
  WFDriverInputParam wdInParam;
  WFDriverOutputParamEnume wdOutParamEnume;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(WorkflowDriverManagerTest.class);
    log.info("------------------------------------");

    wd = new WorkflowDriver();
    wd.setContextPath("/context");
    wd.setFlowDriverName("FlowDriverName");
    wd.setMemo("memo");
    wd.setReadURL("ReadURL");
    wd.setWriteURL("WriteURL");

    wdOutParam = new WFDriverOutputParam();
    wdOutParam.setParamAlias("ParamAlias");
    wdOutParam.setParamName("ParamName");

    wdInParam = new WFDriverInputParam();
    wdInParam.setParamAlias("ParamAlias");
    wdInParam.setParamName("ParamName");

    wdOutParamEnume = new WFDriverOutputParamEnume();
    wdOutParamEnume.setDriverOutputParamEnumeValue("OutputParamEnumeValue");

    workflowDriverManager.saveWorkflowDriver(wd);
    workflowDriverManager.addDriverInputParam(wd.getFlowDriverID().toString(),
                                              wdInParam);
    workflowDriverManager.addDriverOutputParam(wd.getFlowDriverID().toString(),
                                               wdOutParam);
    workflowDriverManager.addDriverOutputParamEnume(wdOutParam.
        getDriverOutputParamID().toString(),
        wdOutParamEnume);
  }

  public void testAddDriverInputParam() {
    WFDriverInputParam driverInputParam = new WFDriverInputParam();
    driverInputParam.setParamName("ParamName");
    driverInputParam.setParamAlias("ParamAlias");
    workflowDriverManager.addDriverInputParam(wd.getFlowDriverID().toString(),
                                              driverInputParam);
    assertEquals("return value",
                 2,
                 workflowDriverManager.getWorkflowDriver(wd.getFlowDriverID().
        toString()).getWfDriverInputParams().size());
    assertEquals("return value",
                 "ParamName",
                 ( (WFDriverInputParam) workflowDriverManager.getWorkflowDriver(
        wd.getFlowDriverID().
        toString()).getWfDriverInputParams().get(1)).getParamName());

    log.info("-----------ParamName");
  }

  public void testAddDriverOutputParam() {
    WFDriverOutputParam driverOutputParam = new WFDriverOutputParam();
    driverOutputParam.setParamName("ParamName");
    driverOutputParam.setParamAlias("ParamAlias");
    workflowDriverManager.addDriverOutputParam(wd.getFlowDriverID().toString(),
                                               driverOutputParam);
    assertEquals("return value",
                 2,
                 workflowDriverManager.getWorkflowDriver(wd.getFlowDriverID().
        toString()).getWfDriverOutputParams().size());
    assertEquals("return value",
                 "ParamName",
                 ( (WFDriverOutputParam) workflowDriverManager.
                  getWorkflowDriver(
        wd.getFlowDriverID().
        toString()).getWfDriverOutputParams().get(1)).getParamName());

    log.info("ParamName");
  }

  public void testAddDriverOutputParamEnume() {
    WFDriverOutputParamEnume driverOutputParamEnume = new
        WFDriverOutputParamEnume();
    driverOutputParamEnume.setDriverOutputParamEnumeValue("ParamName");
    workflowDriverManager.addDriverOutputParamEnume(wdOutParam.
        getDriverOutputParamID().toString(),
        driverOutputParamEnume);

    assertEquals("return value",
                 2,
                 wdOutParam.getDriverOutputParamEnumes().size());
    assertEquals("return value",
                 "ParamName",
                 ( (WFDriverOutputParamEnume) wdOutParam.
                  getDriverOutputParamEnumes().get(1)).
                 getDriverOutputParamEnumeValue());

    log.info("ParamName");
  }

  public void testGetAllDriverContextPath() {
    List actualReturn = workflowDriverManager.getAllDriverContextPath();
    assertEquals("return value", 1, actualReturn.size());
    log.info("1 DriverContextPath");
  }

  public void testGetAllWorkflowDrivers() {
    List actualReturn = workflowDriverManager.getAllWorkflowDrivers();
    assertEquals("return value", 1, actualReturn.size());
    log.info("1 WorkflowDriver");
  }

  public void testGetFlowDriversByContextPath() {
    String contextPath = "/context";
    List actualReturn = workflowDriverManager.getFlowDriversByContextPath(
        contextPath);
    assertEquals("return value", 1, actualReturn.size());
    log.info("1 /context");
  }

  public void testGetWorkflowDriver() {
    WorkflowDriver actualReturn = workflowDriverManager.getWorkflowDriver(
        wd.getFlowDriverID().toString());
    assertEquals("return value", "/context", actualReturn.getContextPath());
    log.info(actualReturn.getFlowDriverID());
  }

  public void testRemoveDriverInputParam() {
    workflowDriverManager.removeDriverInputParam(wdInParam.
                                                 getDriverInputParamID().
                                                 toString());
    assertEquals("return value",
                 0,
                 workflowDriverManager.getWorkflowDriver(wd.getFlowDriverID().
        toString()).getWfDriverInputParams().size());
    log.info("0 InputParam");
  }

  public void testRemoveDriverOutputParam() {
    workflowDriverManager.removeDriverOutputParam(wdOutParam.
                                                  getDriverOutputParamID().
                                                  toString());
    assertEquals("return value",
                 0,
                 workflowDriverManager.getWorkflowDriver(wd.getFlowDriverID().
        toString()).getWfDriverOutputParams().size());
    log.info("0 OutputParam");
  }

  public void testRemoveDriverOutputParamEnume() {
    workflowDriverManager.removeDriverOutputParamEnume(
        wdOutParamEnume.getDriverOutputParamEnumeID().toString());
    assertEquals("return value",
                 0,
                 wdOutParam.getDriverOutputParamEnumes().size());

    log.info("0 OutputParamEnume");
  }

  public void testRemoveWorkflowDriver() {
    try {
      workflowDriverManager.removeWorkflowDriver("88888");
      fail("删除一个不存在的WorkflowDriver却没有报错");
    }
    catch (Exception e) {
      log.info(e);
    }
  }

  public void testSaveWorkflowDriver() {
    wd.setFlowDriverName("xxxxx");
    workflowDriverManager.saveWorkflowDriver(wd);

    WorkflowDriver actualReturn = workflowDriverManager.getWorkflowDriver(
        wd.getFlowDriverID().toString());
    assertEquals("return value", "xxxxx", actualReturn.getFlowDriverName());
    log.info(actualReturn.getFlowDriverName());
  }
  public void setWorkflowDriverManager(WorkflowDriverManager workflowDriverManager) {
    this.workflowDriverManager = workflowDriverManager;
  }

}
