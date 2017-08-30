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

import java.util.*;
import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.powerstone.AbstractSpringTestCase;

public class WorkflowDriverDAOTest
    extends AbstractSpringTestCase {
  private WorkflowDriverDAO dao = null;
  WorkflowDriver wd = null;
  WFDriverInputParam wdInParam = null;
  WFDriverOutputParam wdOutParam = null;
  WFDriverOutputParamEnume techStateY = null;
  WFDriverOutputParamEnume techStateN = null;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(WorkflowDriverDAOTest.class);

    if (wd != null && wd.getFlowDriverID() != null) {
      dao.removeWorkflowDriver(wd.getFlowDriverID());
    }

    techStateY = new WFDriverOutputParamEnume();
    techStateY.setDriverOutputParamEnumeValue("Y");
    techStateN = new WFDriverOutputParamEnume();
    techStateN.setDriverOutputParamEnumeValue("N");

    wdOutParam = new WFDriverOutputParam();
    wdOutParam.setParamAlias("ParamAlias");
    wdOutParam.setParamName("ParamName1");
    wdOutParam.addParamEnume(techStateY);
    wdOutParam.addParamEnume(techStateN);

    wdInParam = new WFDriverInputParam();
    wdInParam.setParamAlias("ParamAlias2");
    wdInParam.setParamName("ParamName2");

    wd = new WorkflowDriver();
    wd.setContextPath("/context");
    wd.setFlowDriverName("FlowDriverName1");
    wd.setMemo("memo");
    wd.setReadURL("ReadURL1");
    wd.setWriteURL("WriteURL1");
    wd.addOutputParam(wdOutParam);
    wd.addInputParam(wdInParam);

    dao.saveWorkflowDriver(wd);
  }

  public void testFindDriverByReadDO() {
    List actualReturn = dao.findDriverByReadDO("/context", "ReadURL1");
    assertTrue("dao.findDriverByReadDO().size==1", actualReturn.size() == 1);
    assertEquals("return value", wd.getFlowDriverID(),
                 ( (WorkflowDriver) actualReturn.get(0)).getFlowDriverID());
    log.info("FindDriverByReadDO[ReadURL=" +
             ( (WorkflowDriver) actualReturn.get(0)).getReadURL() + "]");
  }

  public void testFindDriverByWriteDO() {
    List actualReturn = dao.findDriverByWriteDO("/context", "WriteURL1");
    assertTrue("dao.findDriverByWriteDO().size==1", actualReturn.size() == 1);
    assertEquals("return value", wd.getFlowDriverID(),
                 ( (WorkflowDriver) actualReturn.get(0)).getFlowDriverID());
    log.info("FindDriverByReadDO[WriteURL=" +
             ( (WorkflowDriver) actualReturn.get(0)).getWriteURL() + "]");
  }

  public void testGetAllDriverContextPath() {
    List actualReturn = dao.getAllDriverContextPath();
    assertTrue("dao.getAllDriverContextPath().size==1",
               actualReturn.size() == 1);
    assertEquals("return value", "/context",
                 (String) actualReturn.get(0));
    log.info("getAllDriverContextPath[" + (String) actualReturn.get(0) + "]");
  }

  public void testGetAllWorkflowDrivers() {
    List actualReturn = dao.getAllWorkflowDrivers();
    assertTrue("dao.getAllWorkflowDrivers().size==1",
               actualReturn.size() == 1);
    assertEquals("return value", wd.getFlowDriverID(),
                 ( (WorkflowDriver) actualReturn.get(0)).getFlowDriverID());
    log.info("GetAllWorkflowDrivers[size=" + actualReturn.size() + "]");
  }

  public void testGetDriverInputParam() {
    WFDriverInputParam actualReturn = dao.getDriverInputParam(
        wdInParam.getDriverInputParamID());
    assertEquals("return value", "ParamAlias2", actualReturn.getParamAlias());
    log.info("getDriverInputParam[ParamAlias2]");
  }

  public void testGetDriverOutputParam() {
    WFDriverOutputParam actualReturn = dao.getDriverOutputParam(
        wdOutParam.getDriverOutputParamID());
    assertEquals("return value", "ParamAlias", actualReturn.getParamAlias());
    log.info("getDriverOutputParam[ParamAlias]");
  }

  public void testGetDriverOutputParamEnume() {
    WFDriverOutputParamEnume actualReturn = dao.getDriverOutputParamEnume(
        techStateN.getDriverOutputParamEnumeID());
    assertEquals("return value", "N",
                 actualReturn.getDriverOutputParamEnumeValue());
    log.info("getDriverOutputParamEnumeValue[N]");
  }

  public void testGetFlowDriversByContextPath() {
    List actualReturn = dao.getFlowDriversByContextPath("/context");
    assertTrue("dao.getFlowDriversByContextPath().size==1",
               actualReturn.size() == 1);
    assertEquals("return value", wd.getFlowDriverID(),
                 ( (WorkflowDriver) actualReturn.get(0)).getFlowDriverID());
    log.info("GetFlowDriversByContextPath[ReadURL=" +
             ( (WorkflowDriver) actualReturn.get(0)).getReadURL() + "]");
  }

  public void testGetWorkflowDriver() {
    assertEquals("return value", wd.getFlowDriverName(),
                 dao.getWorkflowDriver(wd.getFlowDriverID()).getFlowDriverName());
    log.info("GetWorkflowDriver[DriverName=" +
             wd.getFlowDriverName() + "]");
  }

  public void testRemoveWorkflowDriver() {
    try {
      dao.removeWorkflowDriver(new Long(100897));
      fail("删除一个不存在的WorkflowDriver didn't throw DataAccessException");
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
    log.info(wd.getFlowDriverName());
  }

  public void testSaveDriverOutputParam() {
    wdOutParam.setParamAlias("daquan");
    dao.saveDriverOutputParam(wdOutParam);
    assertEquals("return value", "daquan",
                 dao.getDriverOutputParam(wdOutParam.getDriverOutputParamID()).
                 getParamAlias());
    log.info("daquan");
  }

  public void testSaveWorkflowDriver() {
    wd.setMemo("daquan_memo");
    dao.saveWorkflowDriver(wd);
    assertEquals("return value", "daquan_memo",
                 dao.getWorkflowDriver(wd.getFlowDriverID()).getMemo());
    log.info("daquan_memo");
  }
  public void setDao(WorkflowDriverDAO dao) {
    this.dao = dao;
  }

}
