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

import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.powerstone.AbstractSpringTestCase;

public class FlowTaskDAOTest
    extends AbstractSpringTestCase {
  private FlowTaskDAO flowTaskDAO = null;
  private FlowProcDAO flowProcDAO;
  FlowProcTransaction procTransaction = null;
  FlowTask flowTask = null;

  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowTaskDAOTest.class);

    procTransaction = new FlowProcTransaction();
    flowTask = new FlowTask();
    flowTask.setCreateTime("CreateTime1");
    flowTask.free();
    procTransaction.addFlowTask(flowTask);
    flowTask.checkOutTask("user1");

    flowProcDAO.saveFlowProcTransaction(procTransaction);
  }

  public void testGetFlowTask() {
    assertEquals("return value",
                 "CreateTime1",
                 flowTaskDAO.getFlowTask(flowTask.getTaskID()).getCreateTime());
    log.info("CreateTime1");
  }

  public void testGetFlowTaskRole() {
    try {
      flowTaskDAO.getFlowTaskRole(new Long("1234"));
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
    log.info("1234");
  }

  public void testGetFlowTaskUser() {
    try {
      flowTaskDAO.getFlowTaskUser(new Long("2345"));
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
    log.info("2345");
  }

  public void testIsTaskOwner() {
    this.assertTrue(flowTaskDAO.isTaskOwner("user1", flowTask.getTaskID()));
    log.info("user1");
  }

  public void testRemoveFlowTask() {
    try {
      flowTaskDAO.removeFlowTask(new Long("444"));
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
    log.info("444");
  }

  public void testSaveFlowTask() {
    flowTask.setCreateTime("haha");
    flowTaskDAO.saveFlowTask(flowTask);

    assertEquals("return value",
                 "haha",
                 flowTaskDAO.getFlowTask(flowTask.getTaskID()).getCreateTime());
    log.info("haha");
  }
  public void setFlowProcDAO(FlowProcDAO flowProcDAO) {
    this.flowProcDAO = flowProcDAO;
  }
  public void setFlowTaskDAO(FlowTaskDAO flowTaskDAO) {
    this.flowTaskDAO = flowTaskDAO;
  }
}
