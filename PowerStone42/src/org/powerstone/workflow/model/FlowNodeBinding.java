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
package org.powerstone.workflow.model;

import java.util.*;

import org.apache.commons.lang.builder.*;
import org.apache.commons.logging.*;

/**工作流部署的节点绑定信息
 * @hibernate.class table="WF_FLOW_NODE_BINDING"
 * @hibernate.query name="FlowNodeBindingsByDriver"
 *  query="select fnb from FlowNodeBinding fnb inner join fnb.workflowDriver wfd where wfd.flowDriverID = ?"

 * @hibernate.query name="FlowNodeBindsByUserPerformer"
 *  query="select fup.flowNodeBinding from FlowUserPerformer fup where fup.userID = ?"

 * @hibernate.query name="FlowNodeBindsByRolePerformer"
 *  query="select frp.flowNodeBinding from FlowRolePerformer frp where frp.roleID = ?"
 * <p>Title: PowerStone</p>
 */

public class FlowNodeBinding
    extends BaseObject {
  public final static String FLOW_PERFORMER_STATIC = "1";
  public final static String FLOW_PERFORMER_FOUNDER = "2";
  public final static String FLOW_PERFORMER_OTHER_PERFORMER = "3";
  public final static String FLOW_PERFORMER_VARIABLE = "4";
  public final static String FLOW_PERFORMER_RULE = "5";
  public final static String FLOW_PERFORMER_ASSIGN = "6";
  private static Log log = LogFactory.getLog(FlowNodeBinding.class);
  private Long nodeBindingID = new Long( -1);
  private FlowDeploy flowDeploy;
  private String flowNodeID;
  private WorkflowDriver workflowDriver;
  private List flowNodeOutputParamBindings = new ArrayList();
  private List flowNodeInputParamBindings = new ArrayList();
  private String performerRule = FLOW_PERFORMER_STATIC;
  private String performerDetail;
  private List flowUserPerformers = new ArrayList();
  private List flowRolePerformers = new ArrayList();

  /**
   * @hibernate.id column="PK_NODE_BINDING_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getNodeBindingID() {
    return nodeBindingID;
  }

  public void setNodeBindingID(Long nodeBindingID) {
    this.nodeBindingID = nodeBindingID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_DEPLOY_ID"
   * class="org.powerstone.workflow.model.FlowDeploy"
   * @return FlowDeploy
   */
  public FlowDeploy getFlowDeploy() {
    return flowDeploy;
  }

  public void setFlowDeploy(FlowDeploy flowDeploy) {
    this.flowDeploy = flowDeploy;
  }

  /**
   * @hibernate.property
   * 		column="VC_FLOW_NODE_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getFlowNodeID() {
    return flowNodeID;
  }

  public void setFlowNodeID(String flowNodeID) {
    this.flowNodeID = flowNodeID;
  }

  /**
   * @hibernate.property
   * 		column="VC_PERFORMER_RULE"
   * 		length="10"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getPerformerRule() {
    return performerRule;
  }

  public void setPerformerRule(String performerRule) {
    this.performerRule = performerRule;
  }

  /**
   * @hibernate.property
   * 		column="VC_PERFORMER_DETAIL"
   * 		length="255"
   * 		type="string"
   *            not-null="false"
   * @return String
   */
  public String getPerformerDetail() {
    return performerDetail;
  }

  public void setPerformerDetail(String performerDetail) {
    this.performerDetail = performerDetail;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_DRIVER_ID"
   * class="org.powerstone.workflow.model.WorkflowDriver"
   * @return WorkflowDriver
   */
  public WorkflowDriver getWorkflowDriver() {
    return workflowDriver;
  }

  public void setWorkflowDriver(WorkflowDriver workflowDriver) {
    this.workflowDriver = workflowDriver;
  }

  /**
   * @hibernate.bag name="flowUserPerformers"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_NODE_BINDING_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowUserPerformer"
   * @return List
   */
  public List getFlowUserPerformers() {
    return flowUserPerformers;
  }

  /**
   * @hibernate.bag name="flowRolePerformers"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_NODE_BINDING_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowRolePerformer"
   * @return List
   */
  public List getFlowRolePerformers() {
    return flowRolePerformers;
  }

  public void setFlowUserPerformers(List flowUserPerformers) {
    this.flowUserPerformers = flowUserPerformers;
  }

  public void setFlowRolePerformers(List flowRolePerformers) {
    this.flowRolePerformers = flowRolePerformers;
  }

  /**
   * @hibernate.bag name="flowNodeOutputParamBindings"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_NODE_BINDING_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowNodeOutputParamBinding"
   * @return List
   */
  public List getFlowNodeOutputParamBindings() {
    return flowNodeOutputParamBindings;
  }

  public void addFlowNodeOutputParamBinding(FlowNodeOutputParamBinding
                                            nodeOutputParamBinding) {
    nodeOutputParamBinding.setFlowNodeBinding(this);
    getFlowNodeOutputParamBindings().add(nodeOutputParamBinding);
  }

  public void setFlowNodeOutputParamBindings(List flowNodeOutputParamBindings) {
    this.flowNodeOutputParamBindings = flowNodeOutputParamBindings;
  }

  /**
   * @hibernate.bag name="flowNodeInputParamBindings"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_NODE_BINDING_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowNodeInputParamBinding"
   * @return List
   */
  public List getFlowNodeInputParamBindings() {
    return flowNodeInputParamBindings;
  }

  public void addFlowNodeInputParamBinding(FlowNodeInputParamBinding
                                           nodeInputParamBinding) {
    nodeInputParamBinding.setFlowNodeBinding(this);
    getFlowNodeInputParamBindings().add(nodeInputParamBinding);
  }

//  public void removeFlowNodeInputParamBinding(FlowNodeInputParamBinding
//                                              nodeInputParamBinding) {
//    getFlowNodeInputParamBindings().remove(nodeInputParamBinding);
//    nodeInputParamBinding.setFlowNodeBinding(null);
//  }
//
//  public void addFlowNodeInputParamBinding(FlowNodeInputParamBinding
//                                           nodeInputParamBinding) {
//    getFlowNodeInputParamBindings().add(nodeInputParamBinding);
//    nodeInputParamBinding.setFlowNodeBinding(this);
//  }

  public WFDriverInputParam findDriverInputParamByNodeParamID(String
      nodeInputParamID) {
    for (Iterator it = getFlowNodeInputParamBindings().iterator(); it.hasNext(); ) {
      FlowNodeInputParamBinding nodeInputParamBinding =
          (FlowNodeInputParamBinding) it.next();
      if (nodeInputParamBinding.getFlowNodeParamID().equals(nodeInputParamID)) {
        return nodeInputParamBinding.getWfDriverInputParam();
      }
    }
    return null;
  }

  public FlowNodeInputParamBinding findNodeInputParamBindingByNodeParamID(
      String
      nodeInputParamID) {
    for (Iterator it = getFlowNodeInputParamBindings().iterator(); it.hasNext(); ) {
      FlowNodeInputParamBinding nodeInputParamBinding =
          (FlowNodeInputParamBinding) it.next();
      if (nodeInputParamBinding.getFlowNodeParamID().equals(nodeInputParamID)) {
        return nodeInputParamBinding;
      }
    }
    return null;
  }

  public WFDriverOutputParam findDriverOutputParamByNodeParamID(String
      nodeOutputParamID) {
    for (Iterator it = getFlowNodeOutputParamBindings().iterator(); it.hasNext(); ) {
      FlowNodeOutputParamBinding nodeOutputParamBinding =
          (FlowNodeOutputParamBinding) it.next();
      if (nodeOutputParamBinding.getFlowNodeParamID().equals(nodeOutputParamID)) {
        return nodeOutputParamBinding.getWfDriverOutputParam();
      }
    }
    return null;
  }

  public FlowNodeOutputParamBinding findNodeOutputParamBindingByNodeParamID(
      String
      nodeOutputParamID) {
    for (Iterator it = getFlowNodeOutputParamBindings().iterator(); it.hasNext(); ) {
      FlowNodeOutputParamBinding nodeOutputParamBinding =
          (FlowNodeOutputParamBinding) it.next();
      if (nodeOutputParamBinding.getFlowNodeParamID().equals(nodeOutputParamID)) {
        return nodeOutputParamBinding;
      }
    }
    return null;
  }

  public FlowNodeOutputParamBinding findNodeOutputParamBindingByDriverParamName(
      String driverOutputParamName) {

    for (Iterator it = getFlowNodeOutputParamBindings().iterator(); it.hasNext(); ) {
      FlowNodeOutputParamBinding nodeOutputParamBinding =
          (FlowNodeOutputParamBinding) it.next();
      if (log.isDebugEnabled()) {
        log.debug("equals[" +
                  nodeOutputParamBinding.getWfDriverOutputParam().getParamName() +
                  "|" + driverOutputParamName + "]");
      }

      if (nodeOutputParamBinding.getWfDriverOutputParam().getParamName().equals(
          driverOutputParamName)) {
        return nodeOutputParamBinding;
      }
    }
    return null;
  }

  public void setFlowNodeInputParamBindings(List flowNodeInputParamBindings) {
    this.flowNodeInputParamBindings = flowNodeInputParamBindings;
  }

  public boolean isAssign() {
    return FLOW_PERFORMER_ASSIGN.equals(getPerformerRule());
  }

  public boolean isFounder() {
    return FLOW_PERFORMER_FOUNDER.equals(getPerformerRule());
  }

  public boolean isOtherPerformer() {
    return FLOW_PERFORMER_OTHER_PERFORMER.equals(getPerformerRule());
  }

  public boolean isRule() {
    return FLOW_PERFORMER_RULE.equals(getPerformerRule());
  }

  public boolean isStatic() {
    return FLOW_PERFORMER_STATIC.equals(getPerformerRule());
  }

  public boolean isVariable() {
    return FLOW_PERFORMER_VARIABLE.equals(getPerformerRule());
  }

  public void enableFounder() {
    this.setPerformerRule(this.FLOW_PERFORMER_FOUNDER);
  }

  public void enableOtherPerformer() {
    this.setPerformerRule(this.FLOW_PERFORMER_OTHER_PERFORMER);
  }

  public void enableAssign() {
    this.setPerformerRule(this.FLOW_PERFORMER_ASSIGN);
  }

  public void enableVariable() {
    this.setPerformerRule(this.FLOW_PERFORMER_VARIABLE);
  }

  public void enableRule() {
    this.setPerformerRule(this.FLOW_PERFORMER_RULE);
  }

  public void enableStatic() {
    this.setPerformerRule(this.FLOW_PERFORMER_STATIC);
    this.setPerformerDetail(null);
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowNodeBinding)) {
      return false;
    }
    FlowNodeBinding fnb = (FlowNodeBinding) object;
    return new EqualsBuilder().
        append(this.getNodeBindingID().toString(),
               fnb.getNodeBindingID().toString())
        .append(this.getFlowNodeID(), fnb.getFlowNodeID()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1356335803, 137569255).append(
        this.getNodeBindingID().toString()).append(this.getFlowNodeID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("nodeBindingID", this.getNodeBindingID().toString())
        .append("flowNodeID", this.getFlowNodeID())
        .toString();
  }

  public void addUserPerformer(Long userID) {
    if(this.hasUserPerformer(userID)){
      log.warn("User["+userID+"] has already bee performer!");
      return;
    }
    FlowUserPerformer fup = new FlowUserPerformer();
    fup.setFlowNodeBinding(this);
    fup.setUserID(userID);
    this.getFlowUserPerformers().add(fup);
  }

  public void addRolePerformer(Long roleID) {
    if(this.hasRolePerformer(roleID)){
      log.warn("Role["+roleID+"] has already bee performer!");
      return;
    }

    FlowRolePerformer frp = new FlowRolePerformer();
    frp.setFlowNodeBinding(this);
    frp.setRoleID(roleID);
    this.getFlowRolePerformers().add(frp);
  }

  public boolean hasUserPerformer(Long userID) {
    for (Iterator it = this.getFlowUserPerformers().iterator(); it.hasNext(); ) {
      FlowUserPerformer fup = (FlowUserPerformer) it.next();
      if (fup.getUserID().equals(userID)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasRolePerformer(Long roleID) {
    for (Iterator it = this.getFlowRolePerformers().iterator(); it.hasNext(); ) {
      FlowRolePerformer frp = (FlowRolePerformer) it.next();
      if (frp.getRoleID().equals(roleID)) {
        return true;
      }
    }
    return false;
  }

  public void removeUserPerformer(Long userID) {
    FlowUserPerformer target = null;
    for (Iterator it = this.getFlowUserPerformers().iterator(); it.hasNext(); ) {
      target = (FlowUserPerformer) it.next();
      if (target.getUserID().equals(userID)) {
        break;
      }
    }
    if (target == null) {
      log.warn("User[" + userID + "] is not performer of FlowNodeBinding[" +
               this +"]");
    }
    else {
      target.setFlowNodeBinding(null);
      getFlowUserPerformers().remove(target);
    }
  }

  public void removeRolePerformer(Long roleID) {
    FlowRolePerformer target = null;
    for (Iterator it = this.getFlowRolePerformers().iterator(); it.hasNext(); ) {
      target = (FlowRolePerformer) it.next();
      if (target.getRoleID().equals(roleID)) {
        break;
      }
    }
    if (target == null) {
      log.warn("Role[" + roleID + "] is not performer of FlowNodeBinding[" +
               this +"]");
    }
    else {
      target.setFlowNodeBinding(null);
      getFlowRolePerformers().remove(target);
    }
  }
}
