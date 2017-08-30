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

import org.powerstone.AbstractSpringTestCase;
import org.powerstone.workflow.model.*;
import org.apache.commons.logging.LogFactory;

public class FlowDeployManager2Test
    extends AbstractSpringTestCase {
  private FlowDeployManager flowDeployManager = null;
  FlowDeploy flowDeploy;
  FlowNodeBinding flowNodeBinding;
  protected void onSetUpInTransaction() throws Exception {
    log = LogFactory.getLog(FlowDeployManager2Test.class);

    flowDeploy = new FlowDeploy();
    flowDeploy.setCreateTime("-------CreateTime----------");
    flowDeploy.setCurrentState("CurrentState");
    flowDeploy.setFlowDeployName("FlowDeployName");
    flowDeploy.setMemo("Memo");
    flowNodeBinding = new FlowNodeBinding();
    flowNodeBinding.setFlowNodeID("FlowNodeID-------------");
    flowDeploy.addFlowNodeBinding(flowNodeBinding);

    flowDeployManager.saveFlowDeploy(flowDeploy);
  }


  public void testEnableFounder() {
    flowDeployManager.enableFounder(
        flowNodeBinding.getNodeBindingID().toString());
    assertTrue("isFounder",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).isFounder());
    log.info(flowDeploy.getFlowDeployID() + "|isFounder");
  }

  public void testEnableStatic() {
    flowDeployManager.enableStatic(
        flowNodeBinding.getNodeBindingID().toString());
    assertTrue("isStatic",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).isStatic());
    assertNull("getPerformerDetail",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).getPerformerDetail());
    log.info(flowDeploy.getFlowDeployID() + "|isStatic");
  }

  public void testUpdateAssign() {
    flowDeployManager.updateAssign(flowNodeBinding.getNodeBindingID().toString(),
                                   "flowNodeID");
    assertTrue("isAssign",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).isAssign());
    assertEquals("getPerformerDetail", "flowNodeID",
                 flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).getPerformerDetail());
    log.info(flowDeploy.getFlowDeployID() + "|isAssign");
  }

  public void testUpdateOtherPerformer() {
    flowDeployManager.updateOtherPerformer(flowNodeBinding.getNodeBindingID().
                                           toString(),
                                           "OtherPerformer");
    assertTrue("isOtherPerformer",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).isOtherPerformer());
    assertEquals("getPerformerDetail", "OtherPerformer",
                 flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).getPerformerDetail());
    log.info(flowDeploy.getFlowDeployID() + "|isOtherPerformer");
  }

  public void testUpdateRule() {
    flowDeployManager.updateRule(flowNodeBinding.getNodeBindingID().toString(),
                                 "Rule");
    assertTrue("isAssign",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).isRule());
    assertEquals("getPerformerDetail", "Rule",
                 flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).getPerformerDetail());
    log.info(flowDeploy.getFlowDeployID() + "|isAssign");
  }

  public void testUpdateVariable() {
    flowDeployManager.updateVariable(flowNodeBinding.getNodeBindingID().
                                     toString(),
                                     "VariableID");
    assertTrue("isVariable",
               flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).isVariable());
    assertEquals("getPerformerDetail", "VariableID",
                 flowDeployManager.getFlowNodeBinding(flowNodeBinding.
        getNodeBindingID().toString()).getPerformerDetail());
    log.info(flowDeploy.getFlowDeployID() + "|isVariable");
  }
  public void setFlowDeployManager(FlowDeployManager flowDeployManager) {
    this.flowDeployManager = flowDeployManager;
  }
}
