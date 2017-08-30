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

import java.util.*;

import org.powerstone.workflow.dao.*;
import org.powerstone.workflow.model.*;

public interface FlowDeployManager {

  public void setFlowDeployDAO(FlowDeployDAO dao);

  public void setWorkflowDriverManager(WorkflowDriverManager flowDriverManager);

  public FlowDeploy getFlowDeploy(String flowDeployID);

  /**
   * 没有单元测试
   * @param flowNodeBindingID String
   * @return FlowNodeBinding
   */
  public FlowNodeBinding getFlowNodeBinding(String flowNodeBindingID);

  public List findFlowNodeBindingsByDriver(String flowDriverID);

  public FlowDeploy saveFlowDeploy(FlowDeploy flowDeploy);

  public FlowDeploy updateFlowDeploy(FlowDeploy flowDeploy);

  public FlowDeploy enableFlowDeploy(String flowDeployID);

  public FlowDeploy disableFlowDeploy(String flowDeployID);

  public FlowNodeBinding updateFlowNodeBinding(String flowDeployID,
                                               String nodeID,
                                               String flowDriverID);

  public FlowNodeBinding updateFlowNodeParamBinding(
      String flowNodeBindingID,
      HashMap inputParamMap,
      HashMap outputParamMap,
      HashMap outoutParamEnumeMap);

  public FlowNodeBinding saveFlowNodeBinding(FlowNodeBinding flowNodeBinding);

  public void removeFlowDeploy(String flowDeployID);

  public void enableFounder(String flowNodeBindingID);
  public void enableStatic(String flowNodeBindingID);
  public FlowNodeBinding updateOtherPerformer(String flowNodeBindingID,String flowNodeID);
  public FlowNodeBinding updateAssign(String flowNodeBindingID,String flowNodeID);
  public FlowNodeBinding updateVariable(String flowNodeBindingID,String variableID);
  public FlowNodeBinding updateRule(String flowNodeBindingID,String ruleDetail);

  public void addUserPerformer(String userID, String nodeBindingID);

  public void addRolePerformer(String roleID, String nodeBindingID);

  public boolean isUserPerformer(String userID, String nodeBindingID);

  public boolean isRolePerformer(String roleID, String nodeBindingID);

  public boolean isNodePerformer(String userID, String nodeBindingID);

  public List findUsersByNodeBinding(String nodeBindingID);

  public List findRolesByNodeBinding(String nodeBindingID);

  public void removeUserPerformer(String userID, String nodeBindingID);

  public void removeRolePerformer(String roleID, String nodeBindingID);

  public List calcAllMyPerformingNodes(String userID);
}
