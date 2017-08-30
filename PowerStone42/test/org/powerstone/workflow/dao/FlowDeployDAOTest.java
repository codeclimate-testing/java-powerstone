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

public class FlowDeployDAOTest
    extends AbstractSpringTestCase {
  private FlowDeployDAO flowDeployDAO = null;
  FlowDeploy flowDeploy;
  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowMetaFileDAOTest.class);

    flowDeploy = new FlowDeploy();
    flowDeploy.setCreateTime("-------CreateTime----------");
    flowDeploy.setCurrentState("CurrentState");
    flowDeploy.setFlowDeployName("FlowDeployName");
    flowDeploy.setMemo("Memo");
    flowDeployDAO.saveFlowDeploy(flowDeploy);
  }

  public void testGetFlowDeploy() {
    assertEquals("return value",
                 "Memo",
                 flowDeployDAO.getFlowDeploy(flowDeploy.getFlowDeployID()).
                 getMemo());
    log.info("Memo");
  }

  public void testGetFlowNodeBinding() {
    try {
      flowDeployDAO.getFlowNodeBinding(new Long(888));
    }
    catch (DataAccessException d) {
      assertNotNull(d);
      log.info(d);
    }
    log.info("888");
  }

  public void testSaveFlowDeploy() {
    flowDeploy.setMemo("-----Memo--------");
    flowDeployDAO.saveFlowDeploy(flowDeploy);

    assertEquals("return value",
                 "-----Memo--------",
                 flowDeployDAO.getFlowDeploy(flowDeploy.getFlowDeployID()).
                 getMemo());
    log.info("-----Memo--------");
  }

  public void testSaveFlowNodeBinding() {
    FlowNodeBinding flowNodeBinding = new FlowNodeBinding();
    flowNodeBinding.setFlowNodeID("FlowNodeID-------------");
    flowDeploy.addFlowNodeBinding(flowNodeBinding);
    flowDeployDAO.saveFlowDeploy(flowDeploy);

    FlowNodeBinding fnb = (FlowNodeBinding)
        flowDeployDAO.getFlowDeploy(flowDeploy.getFlowDeployID()).
        getFlowNodeBindings().get(0);

    assertEquals("return value",
                 "FlowNodeID-------------",
                 fnb.getFlowNodeID());

    log.info(fnb.getFlowNodeID());
  }
  public void setFlowDeployDAO(FlowDeployDAO flowDeployDAO) {
    this.flowDeployDAO = flowDeployDAO;
  }

}
