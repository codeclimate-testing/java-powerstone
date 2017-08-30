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

import org.powerstone.workflow.service.FlowDeployManager;
import org.powerstone.workflow.dao.FlowDeployDAO;
import org.powerstone.workflow.service.WorkflowDriverManager;
import org.powerstone.workflow.model.FlowDeploy;
import org.powerstone.workflow.model.FlowNodeBinding;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.powerstone.workflow.model.WorkflowDriver;
import java.util.HashMap;
import java.util.Iterator;
import org.powerstone.workflow.model.FlowNodeInputParamBinding;
import org.powerstone.workflow.model.WFDriverInputParam;
import org.powerstone.workflow.model.FlowNodeOutputParamBinding;
import org.powerstone.workflow.model.FlowNodeOutputParamEnumeBinding;
import org.powerstone.workflow.model.WFDriverOutputParam;
import org.powerstone.workflow.model.WFDriverOutputParamEnume;
import java.util.List;
import org.powerstone.ca.CADelegater;
import org.powerstone.ca.model.User;
import org.powerstone.ca.model.Role;
import java.util.ArrayList;
import org.powerstone.workflow.model.FlowUserPerformer;
import org.powerstone.workflow.model.FlowRolePerformer;

public class FlowDeployManagerImpl
    implements FlowDeployManager {
  private static Log log = LogFactory.getLog(FlowDeployManagerImpl.class);
  private FlowDeployDAO flowDeployDAO;
  private WorkflowDriverManager workflowDriverManager;
  private CADelegater caDelegater;
  public void setFlowDeployDAO(FlowDeployDAO dao) {
    this.flowDeployDAO = dao;
  }

  public void setWorkflowDriverManager(WorkflowDriverManager flowDriverManager) {
    this.workflowDriverManager = flowDriverManager;
  }

  public FlowDeploy getFlowDeploy(String flowDeployID) {
    return flowDeployDAO.getFlowDeploy(new Long(flowDeployID));
  }

  /**
   * 没有单元测试
   * @param flowNodeBindingID String
   * @return FlowNodeBinding
   */
  public FlowNodeBinding getFlowNodeBinding(String flowNodeBindingID) {
    return flowDeployDAO.getFlowNodeBinding(new Long(flowNodeBindingID));
  }

  public FlowDeploy saveFlowDeploy(FlowDeploy flowDeploy) {
    flowDeployDAO.saveFlowDeploy(flowDeploy);
    return flowDeploy;
  }

  public FlowDeploy updateFlowDeploy(FlowDeploy flowDeploy){
    FlowDeploy target = this.getFlowDeploy(flowDeploy.getFlowDeployID().toString());
    target.setFlowDeployName(flowDeploy.getFlowDeployName());
    target.setMemo(flowDeploy.getMemo());
    saveFlowDeploy(target);
    return target;
  }

  public FlowDeploy enableFlowDeploy(String flowDeployID) {
    FlowDeploy flowDeploy = this.getFlowDeploy(flowDeployID);
    flowDeploy.enableFlowDeploy();
    saveFlowDeploy(flowDeploy);
    return flowDeploy;
  }

  public FlowDeploy disableFlowDeploy(String flowDeployID) {
    FlowDeploy flowDeploy = this.getFlowDeploy(flowDeployID);
    flowDeploy.disableFlowDeploy();
    saveFlowDeploy(flowDeploy);
    return flowDeploy;
  }

//  public FlowNodeBinding updateFlowNodeBinding(String flowNodeBindingID,
//                                               String flowDriverID) {
//    FlowNodeBinding oldFlowNodeBinding =
//        flowDeployDAO.getFlowNodeBinding(new Long(flowNodeBindingID));
//    FlowDeploy flowDeploy = oldFlowNodeBinding.getFlowDeploy();
//    WorkflowDriver wd = workflowDriverManager.getWorkflowDriver(flowDriverID);
//
//    FlowNodeBinding flowNodeBinding = new FlowNodeBinding();
//    flowNodeBinding.setFlowNodeID(oldFlowNodeBinding.getFlowNodeID());
//    flowNodeBinding.setWorkflowDriver(wd);
//    flowDeploy.addFlowNodeBinding(flowNodeBinding);
//    this.saveFlowDeploy(flowDeploy);
//
//    return flowNodeBinding;
//  }

  public FlowNodeBinding updateFlowNodeBinding(String flowDeployID,
                                               String nodeID,
                                               String flowDriverID) {
    FlowDeploy flowDeploy = this.getFlowDeploy(flowDeployID);
    WorkflowDriver wd = workflowDriverManager.getWorkflowDriver(flowDriverID);

    FlowNodeBinding flowNodeBinding = new FlowNodeBinding();
    flowNodeBinding.setFlowNodeID(nodeID);
    flowNodeBinding.setWorkflowDriver(wd);
    flowDeploy.addFlowNodeBinding(flowNodeBinding);
    this.saveFlowDeploy(flowDeploy);

    return flowNodeBinding;
  }

  public void removeFlowDeploy(String flowDeployID) {
    FlowDeploy flowDeploy = this.getFlowDeploy(flowDeployID);
    //删除流程参与者信息
    //caDelegater.removeResource(flowDeploy);

//    for(Iterator it=flowDeploy.getFlowProcs().iterator();it.hasNext();){
//      FlowProc flowProc=(FlowProc)it.next();
//      this.flowProcManager.removeFlowProc(flowProc.getFlowProcID().toString());
//    }

    //清洗所有的"一对多"
    if (log.isDebugEnabled()) {
      log.debug("试图删除'上有老下有小'的PO[FlowDeploy:" + flowDeployID + "]!!!");
    }
    flowDeploy.clear();
    flowDeploy.getWorkflowMeta().removeFlowDeploy(flowDeploy);
//    flowDeploy.getWorkflowMeta().getFlowDeploies().remove(flowDeploy);
//    flowDeploy.setWorkflowMeta(null);

    this.saveFlowDeploy(flowDeploy);
    //flowDeployDAO.removeFlowDeploy(new Long(flowDeployID));
  }

  public FlowNodeBinding saveFlowNodeBinding(FlowNodeBinding flowNodeBinding) {
    flowDeployDAO.saveFlowNodeBinding(flowNodeBinding);
    return flowNodeBinding;
  }

  public FlowNodeBinding updateFlowNodeParamBinding(String flowNodeBindingID,
      HashMap inputParamMap, HashMap outputParamMap,
      HashMap outoutParamEnumeMap) {
    if (log.isDebugEnabled()) {
      log.debug(flowNodeBindingID);
      log.debug(inputParamMap);
      log.debug(outputParamMap);
      log.debug(outoutParamEnumeMap);
    }

    FlowNodeBinding flowNodeBinding = getFlowNodeBinding(flowNodeBindingID);
    //更新输入参数绑定
    for (Iterator it = inputParamMap.keySet().iterator();
         it.hasNext(); ) {
      String flowNodeParamID = (String) it.next();
      String driverInputParamID = (String) inputParamMap.get(flowNodeParamID);
      WFDriverInputParam driverInputParam =
          this.workflowDriverManager.getDriverInputParam(driverInputParamID);

      FlowNodeInputParamBinding nodeInputParamBinding =
          flowNodeBinding.findNodeInputParamBindingByNodeParamID(
          flowNodeParamID);
      if (nodeInputParamBinding == null) {
        nodeInputParamBinding = new FlowNodeInputParamBinding();
        flowNodeBinding.addFlowNodeInputParamBinding(nodeInputParamBinding);
        nodeInputParamBinding.setFlowNodeParamID(flowNodeParamID);
      }
      nodeInputParamBinding.setWfDriverInputParam(driverInputParam);
    }
    //更新输出参数绑定
    for (Iterator it = outputParamMap.keySet().iterator();
         it.hasNext(); ) {
      String flowNodeParamID = (String) it.next();
      String driverOutputParamID = (String) outputParamMap.get(flowNodeParamID);
      WFDriverOutputParam driverOutputParam =
          this.workflowDriverManager.getDriverOutputParam(driverOutputParamID);

      FlowNodeOutputParamBinding nodeOutputParamBinding =
          flowNodeBinding.findNodeOutputParamBindingByNodeParamID(
          flowNodeParamID);
      if (nodeOutputParamBinding == null) {
        nodeOutputParamBinding = new FlowNodeOutputParamBinding();
        flowNodeBinding.addFlowNodeOutputParamBinding(nodeOutputParamBinding);
        nodeOutputParamBinding.setFlowNodeParamID(flowNodeParamID);
      }
      nodeOutputParamBinding.setWfDriverOutputParam(driverOutputParam);
      //更新输出参数枚举绑定
      for (Iterator it2 = outoutParamEnumeMap.keySet().iterator(); it2.hasNext(); ) {
        String flowNodeParamEnume = (String) it2.next();
        String driverOutputParamEnumeID =
            (String) outoutParamEnumeMap.get(flowNodeParamEnume);
        WFDriverOutputParamEnume driverOutputParamEnume =
            this.workflowDriverManager.getDriverOutputParamEnume(
            driverOutputParamEnumeID);

        FlowNodeOutputParamEnumeBinding nodeOutputParamEnume =
            nodeOutputParamBinding.
            findNodeOutputParamEnumeBindingByNodeParamEnume(
            flowNodeParamEnume);
        if (nodeOutputParamEnume == null) {
          nodeOutputParamEnume = new FlowNodeOutputParamEnumeBinding();

          nodeOutputParamBinding.addFlowNodeOutputParamEnumeBinding(
              nodeOutputParamEnume);

          nodeOutputParamEnume.setNodeOutputParamEnume(flowNodeParamEnume);
        }
        nodeOutputParamEnume.setWfDriverOutputParamEnume(driverOutputParamEnume);
      }
    }
    return this.saveFlowNodeBinding(flowNodeBinding);
  }

  public List findFlowNodeBindingsByDriver(String flowDriverID) {
    return flowDeployDAO.findFlowNodeBindingsByDriver(new Long(flowDriverID));
  }

  public void enableFounder(String flowNodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(flowNodeBindingID);
    nodeBinding.enableFounder();
    this.saveFlowNodeBinding(nodeBinding);
  }

  public void enableStatic(String flowNodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(flowNodeBindingID);
    nodeBinding.enableStatic();
    this.saveFlowNodeBinding(nodeBinding);
  }

  public FlowNodeBinding updateOtherPerformer(String flowNodeBindingID,
                                              String flowNodeID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(flowNodeBindingID);
    nodeBinding.enableOtherPerformer();
    nodeBinding.setPerformerDetail(flowNodeID);
    return this.saveFlowNodeBinding(nodeBinding);
  }

  public FlowNodeBinding updateAssign(String flowNodeBindingID,
                                      String flowNodeID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(flowNodeBindingID);
    nodeBinding.enableAssign();
    nodeBinding.setPerformerDetail(flowNodeID);
    return this.saveFlowNodeBinding(nodeBinding);
  }

  public FlowNodeBinding updateVariable(String flowNodeBindingID,
                                        String variableID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(flowNodeBindingID);
    nodeBinding.enableVariable();
    nodeBinding.setPerformerDetail(variableID);
    return this.saveFlowNodeBinding(nodeBinding);
  }

  public FlowNodeBinding updateRule(String flowNodeBindingID, String ruleDetail) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(flowNodeBindingID);
    nodeBinding.enableRule();
    nodeBinding.setPerformerDetail(ruleDetail);
    return this.saveFlowNodeBinding(nodeBinding);
  }

//------------------------------------------------------------------------------
  public void setCaDelegater(CADelegater caDelegater) {
    this.caDelegater = caDelegater;
  }

  public void addUserPerformer(String userID, String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    nodeBinding.addUserPerformer(new Long(userID));
    this.saveFlowNodeBinding(nodeBinding);
  }

  public void addRolePerformer(String roleID, String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    nodeBinding.addRolePerformer(new Long(roleID));
    this.saveFlowNodeBinding(nodeBinding);
  }

  public boolean isUserPerformer(String userID, String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    return nodeBinding.hasUserPerformer(new Long(userID));
  }

  public boolean isRolePerformer(String roleID, String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    return nodeBinding.hasRolePerformer(new Long(roleID));
  }

  public boolean isNodePerformer(String userID, String nodeBindingID) {
    if (isUserPerformer(userID, nodeBindingID)) {
      return true;
    }
    User user = caDelegater.findUserByUserID(userID);
    for (Iterator it = user.getRoles().iterator(); it.hasNext(); ) {
      Role role = (Role) it.next();
      if (isRolePerformer(role.getRoleID().toString(), nodeBindingID)) {
        return true;
      }
    }
    return false;
  }

  public List findUsersByNodeBinding(String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    List result = new ArrayList();
    for (Iterator it = nodeBinding.getFlowUserPerformers().iterator();
         it.hasNext(); ) {
      FlowUserPerformer fup = (FlowUserPerformer) it.next();
      result.add(caDelegater.findUserByUserID(fup.getUserID().toString()));
    }
    return result;
  }

  public List findRolesByNodeBinding(String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    List result = new ArrayList();
    for (Iterator it = nodeBinding.getFlowRolePerformers().iterator();
         it.hasNext(); ) {
      FlowRolePerformer frp = (FlowRolePerformer) it.next();
      result.add(caDelegater.findRoleByRoleID(frp.getRoleID().toString()));
    }

    return result;
  }

  public void removeUserPerformer(String userID, String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    nodeBinding.removeUserPerformer(new Long(userID));
    this.saveFlowNodeBinding(nodeBinding);
  }

  public void removeRolePerformer(String roleID, String nodeBindingID) {
    FlowNodeBinding nodeBinding = this.getFlowNodeBinding(nodeBindingID);
    nodeBinding.removeRolePerformer(new Long(roleID));
    this.saveFlowNodeBinding(nodeBinding);
  }

  public List calcAllMyPerformingNodes(String userID) {
    List result = new ArrayList();
    User user = caDelegater.findUserByUserID(userID);
    result.addAll(flowDeployDAO.findFlowNodeBindsByUserPerformer(new Long(
        userID)));
    for (Iterator it = user.getRoles().iterator(); it.hasNext(); ) {
      Role role = (Role) it.next();
      result.addAll(flowDeployDAO.findFlowNodeBindsByRolePerformer(role.
          getRoleID()));
    }
    return result;
  }
}
