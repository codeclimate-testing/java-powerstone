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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @hibernate.class table="WF_FLOW_PROC_TRANSACTION"
 * <p>Title: PowerStone</p>
 */

public class FlowProcTransaction
    extends BaseObject {
  private static Log log = LogFactory.getLog(FlowProcTransaction.class);
  public static final String TRANSACTION_STATE_ACTIVE = "active";
  public static final String TRANSACTION_STATE_OVER = "over";

  private Long transactionID = new Long( -1);
  private List flowProcTransitions = new ArrayList();
  private List flowTasks = new ArrayList();
  private FlowProc flowProc;
  private String transactionState = TRANSACTION_STATE_ACTIVE;

  /**
   * @hibernate.id column="PK_FLOW_PROC_TRANSACTION_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(Long transactionID) {
    this.transactionID = transactionID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_PROC_ID"
   * class="org.powerstone.workflow.model.FlowProc"
   * @return FlowProc
   */
  public FlowProc getFlowProc() {
    return flowProc;
  }

  public void setFlowProc(FlowProc flowProc) {
    this.flowProc = flowProc;
  }

  /**
   * @hibernate.bag name="flowProcTransitions"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_PROC_TRANSACTION_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowProcTransition"
   * @return List
   */
  public List getFlowProcTransitions() {
    return flowProcTransitions;
  }

  public void addFlowProcTransition(FlowProcTransition procTransition) {
    procTransition.setFlowProcTransaction(this);
    getFlowProcTransitions().add(procTransition);
  }

  public void setFlowProcTransitions(List flowProcTransitions) {
    this.flowProcTransitions = flowProcTransitions;
  }

  /**
   * @hibernate.bag name="flowTasks"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_PROC_TRANSACTION_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowTask"
   * @return List
   */
  public List getFlowTasks() { //cascade="save-update"
    return flowTasks;
  }

  public void addFlowTask(FlowTask flowTask) {
    flowTask.setFlowProcTransaction(this);
    getFlowTasks().add(flowTask);
  }

  public void removeFlowTask(FlowTask flowTask) {
    int i=getFlowTasks().size();
    getFlowTasks().remove(flowTask);
    flowTask.setFlowProcTransaction(null);
    if(log.isDebugEnabled()){
      log.debug("FlowTask Num["+i+"|"+getFlowTasks().size()+"]");
    }
  }

  public void removeFlowProcTransition(FlowProcTransition fpt) {
    int i=getFlowProcTransitions().size();
    getFlowProcTransitions().remove(fpt);
    fpt.setFlowProcTransaction(null);
    if(log.isDebugEnabled()){
      log.debug("Transitions Num["+i+"|"+getFlowProcTransitions().size()+"]");
    }
  }


  public void setFlowTasks(List flowTasks) {
    this.flowTasks = flowTasks;
  }

  /**
   * @hibernate.property
   * 		column="VC_TRANSACTION_STATE"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getTransactionState() {
    return transactionState;
  }

  public boolean isActive() {
    return (getTransactionState() != null &&
            getTransactionState().equals(TRANSACTION_STATE_ACTIVE));
  }

  public void completeTransaction() {
    this.setTransactionState(TRANSACTION_STATE_OVER);
  }

  public void setTransactionState(String transactionState) {
    this.transactionState = transactionState;
  }

  public void clear() {
    if (this.getFlowProcTransitions().size() > 0) {
      for (Iterator it = getFlowProcTransitions().iterator(); it.hasNext(); ) {
        FlowProcTransition procTransition = (FlowProcTransition) it.next();
        procTransition.setFlowProcTransaction(null);
      }
      getFlowProcTransitions().clear();
    }
    if (this.getFlowTasks().size() > 0) {
      for (Iterator it = getFlowTasks().iterator(); it.hasNext(); ) {
        FlowTask flowTask = (FlowTask) it.next();
        flowTask.setFlowProcTransaction(null);

      }
      getFlowTasks().clear();
    }
  }

  /**
   * 计算一个事物的入口任务节点（他们不属于此事务）
   * @return List
   */
  public List getEntranceTasks() {
    List result = new ArrayList();
    for (Iterator it = getFlowProcTransitions().iterator(); it.hasNext(); ) {
      FlowProcTransition procTransition = (FlowProcTransition) it.next();
      FlowTask ft = this.getFlowProc().getTaskByNode(procTransition.
          getFromNodeID());
      if (ft != null) {
        result.add(ft);
      }
    }
    return result;
  }
  /**
   * 计算此事务内，属于节点nodeID可及的范围（包涵的节点和路径）
   * @param nodeID String
   * @return HashMap
   */
  public HashMap getRangeOfNode(String nodeID) {
    HashMap result = new HashMap();
    for (Iterator it = getTransitionsByFromNode(nodeID).iterator(); it.hasNext(); ) {
      FlowProcTransition procTransition = (FlowProcTransition) it.next();
      result.put(procTransition,"路径("+procTransition.getWorkflowTransitionID()+")");
      FlowTask ft=this.getFlowProc().getTaskByNode(procTransition.getToNodeID());
      if(ft==null){
        result.putAll(getRangeOfNode(procTransition.getToNodeID()));
      }else{
        result.put(ft,"任务("+ft.getTaskID()+")");
      }
    }
    if(log.isDebugEnabled()){
      log.debug(result);
    }
    return result;
  }

  /**
   * getTransitionsByFromNode
   * 计算事务内从nodeID出发的路径
   * @param nodeID String
   * @return List
   */
  private List getTransitionsByFromNode(String nodeID) {
    List result = new ArrayList();
    for (Iterator it = getFlowProcTransitions().iterator(); it.hasNext(); ) {
      FlowProcTransition procTransition = (FlowProcTransition) it.next();
      if(procTransition.getFromNodeID().equals(nodeID)){
        result.add(procTransition);
      }
    }
    return result;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowProcTransaction)) {
      return false;
    }
    FlowProcTransaction fpt = (FlowProcTransaction) object;
    return new EqualsBuilder()
        .append(this.getTransactionID().toString(),
                fpt.getTransactionID().toString())
        .append(this.getTransactionState(),
                fpt.getTransactionState())
        .isEquals();
  }

  public int hashCode() { //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(306335803, 307569255)
        .append(this.getTransactionID().toString())
        .append(this.getTransactionState())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("TransactionID", this.getTransactionID().toString())
        .append("TransactionState", this.getTransactionState())
        .toString();
  }
}
