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

/**
 * @hibernate.class table="WF_FLOW_PROC"
 * @hibernate.query name="ActiveFlowProcsByDeploy"
 *  query="select theFP from FlowProc theFP where theFP.flowProcID in
 *  (select fp.flowProcID from FlowTask ft join ft.flowProcTransaction.flowProc fp
 *  where fp.flowDeploy.flowDeployID = ? and ft.taskState not in (?)
 *  group by fp having count(ft) >0)"

 * 工作流进程
 * <p>Title: PowerStone</p>
 */

public class FlowProc
    extends BaseObject {
  private static Log log = LogFactory.getLog(FlowProc.class);
  private Long flowProcID = new Long( -1);
  private FlowDeploy flowDeploy;
  private String startTime;
  private FlowProc linkFlowProc;
  private String starterUserID;
  private java.util.List flowProcRelativeDatas = new ArrayList();
  private java.util.List linkedFlowProcs = new ArrayList();
  private java.util.List flowProcTransactions = new ArrayList();
  private String previewText;

  /**
   * @hibernate.id column="PK_FLOW_PROC_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getFlowProcID() {
    return flowProcID;
  }

  public void setFlowProcID(Long flowProcID) {
    this.flowProcID = flowProcID;
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
   * 		column="VC_START_TIME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_LINK_FLOW_PROC_ID"
   * class="org.powerstone.workflow.model.FlowProc"
   * @return FlowProc
   */
  public FlowProc getLinkFlowProc() {
    return linkFlowProc;
  }

  public void setLinkFlowProc(FlowProc linkFlowProc) {
    this.linkFlowProc = linkFlowProc;
  }

  /**
   * @hibernate.property
   * 		column="VC_STARTER_USER_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getStarterUserID() {
    return starterUserID;
  }

  public void setStarterUserID(String starterUserID) {
    this.starterUserID = starterUserID;
  }

  /**
   * 返回进程已经打通的路径
   * @return List
   */
  public List getFlowProcTransitions() {
    List result = new ArrayList();
    for (Iterator it = this.getFlowProcTransactions().iterator(); it.hasNext(); ) {
      FlowProcTransaction fpt = (FlowProcTransaction) it.next();
      for (Iterator it2 = fpt.getFlowProcTransitions().iterator(); it2.hasNext(); ) {
        FlowProcTransition ft = (FlowProcTransition) it2.next();
        result.add(ft);
      }
    }
    return result;
  }

  /**
   * 返回进程已产生的任务
   * @return List
   */
  public List getFlowTasks() {
    List result = new ArrayList();
    for (Iterator it = this.getFlowProcTransactions().iterator(); it.hasNext(); ) {
      FlowProcTransaction fpt = (FlowProcTransaction) it.next();
      for (Iterator it2 = fpt.getFlowTasks().iterator(); it2.hasNext(); ) {
        FlowTask ft = (FlowTask) it2.next();
        result.add(ft);
      }
    }
    return result;
  }

  /**
   * getTaskByNode
   *
   * @param nodeID String
   * @return FlowTask
   */
  public FlowTask getTaskByNode(String nodeID) {
    for (Iterator it = getFlowTasks().iterator(); it.hasNext(); ) {
      FlowTask ft = (FlowTask) it.next();
      if (ft.getFlowNodeBinding().getFlowNodeID().equals(nodeID)) {
        return ft;
      }
    }
    log.warn("WorkflowNode[" + nodeID + "]has no Task in FlowProc[" +
             this.getFlowProcID() + "]");
    return null;
  }

  /**
   * 查找节点nodeID的前置事务
   * @param nodeID String
   * @param transitionIgnore FlowProcTransition忽略属于此进程的路径(因为要和并到它里面)
   * @return FlowProcTransaction
   */
  public FlowProcTransaction getPreTransactionOfNode(String nodeID,
      FlowProcTransaction transactionIgnore) {
    for (Iterator it = this.getFlowProcTransitions().iterator(); it.hasNext(); ) {
      FlowProcTransition ft = (FlowProcTransition) it.next();
      if (!ft.getFlowProcTransaction().equals(transactionIgnore) &&
          ft.getToNodeID().equals(nodeID)) {
        FlowProcTransaction result = ft.getFlowProcTransaction();
        if (log.isDebugEnabled()) {
          log.debug("FlowProcTransaction[" + result.getTransactionID() +
                    "]contains tasks[" +
                    result.getFlowTasks().size() + "] and transitions[" +
                    result.getFlowProcTransitions().size() + "]");
        }
        return result;
      }
    }
    log.warn("WorkflowNode[" + nodeID + "]has no PreTransaction in FlowProc[" +
             this.getFlowProcID() + "]");
    return null;
  }

  /**
   * 查找节点nodeID的后置事务
   * @param nodeID String
   * @return FlowProcTransaction
   */
  public FlowProcTransaction getPostTransactionOfNode(String nodeID) {
    for (Iterator it = this.getFlowProcTransitions().iterator(); it.hasNext(); ) {
      FlowProcTransition ft = (FlowProcTransition) it.next();
      if (ft.getFromNodeID().equals(nodeID)) {
        return ft.getFlowProcTransaction();
      }
    }
    log.warn("WorkflowNode[" + nodeID + "]has no PostTransaction in FlowProc[" +
             this.getFlowProcID() + "]");
    return null;
  }

  /**
   * @hibernate.bag name="flowProcRelativeDatas"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_PROC_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowProcRelativeData"
   * @return List
   */
  public List getFlowProcRelativeDatas() {
    return flowProcRelativeDatas;
  }

  public HashMap getProcState() {
    HashMap result = new HashMap();
    if (getFlowProcRelativeDatas().size() > 0) {
      for (Iterator it = getFlowProcRelativeDatas().iterator(); it.hasNext(); ) {
        FlowProcRelativeData procRelativeData = (FlowProcRelativeData) it.next();
//        if (log.isDebugEnabled()) {
//          log.debug("+---------+[" +
//                    procRelativeData.getFlowNodeOutputParamBinding() + "]");
//        }
        if (procRelativeData.getFlowNodeOutputParamBinding() != null) {
          String nodeParamID =
              procRelativeData.getFlowNodeOutputParamBinding().
              getFlowNodeParamID();
          String nodeParamValue =
              procRelativeData.getCorrespondingNodeParamValue();
          result.put(nodeParamID, nodeParamValue);
        }
      }
    }
    if (log.isDebugEnabled()) {
      log.debug("+++++++++++++ProcState[" + result + "]");
    }
    return result;
  }

  /**
   * 返回对应的输入参数，用来给read_do注入参数
   * @return HashMap
   */
  public HashMap generateProcStateForDriver(FlowNodeBinding targetNode) {
    HashMap result = new HashMap();
    if (getFlowProcRelativeDatas().size() > 0) {
      for (Iterator it = getFlowProcRelativeDatas().iterator(); it.hasNext(); ) {
        FlowProcRelativeData procRelativeData = (FlowProcRelativeData) it.next();
        FlowNodeOutputParamBinding nodeOutputParamBinding =
            procRelativeData.getFlowNodeOutputParamBinding();
        if (nodeOutputParamBinding == null) {
          log.warn("发现一个非法的(没有对应的FlowNodeOutputParamBinding)ProcRelativeData(" +
                   procRelativeData + ")");
          continue;
        }
        if (log.isDebugEnabled()) {
          log.debug("procRelativeData[" + procRelativeData +
                    "]对应的FlowNodeParamID---->" +
                    nodeOutputParamBinding.getFlowNodeParamID());
        }
        WFDriverInputParam driverInputParam =
            targetNode.findDriverInputParamByNodeParamID(nodeOutputParamBinding.
            getFlowNodeParamID());
        if (log.isDebugEnabled()) {
          log.debug("ProcRelativeData[" + procRelativeData +
                    "]对应FlowNodeParamID[" +
                    nodeOutputParamBinding.getFlowNodeParamID() +
                    "]->对应了节点[" + targetNode.getFlowNodeID() +
                    "]的WFDriverInputParam[" + driverInputParam + "]");
        }
        String driverParamValue = procRelativeData.getDriverParamValue();
        if (driverInputParam != null) {
          result.put(driverInputParam.getParamName(), driverParamValue);
        }
        else {
          result.put(nodeOutputParamBinding.getWfDriverOutputParam().
                     getParamName(), driverParamValue);
        }
      }
    }

    return result;
  }

  public void addFlowProcRelativeData(FlowProcRelativeData procRelativeData) {
    procRelativeData.setFlowProc(this);
    getFlowProcRelativeDatas().add(procRelativeData);
  }

  public FlowProcRelativeData findProcRelativeDataByDriverParamName(String
      driverParamName) {
    for (Iterator it = getFlowProcRelativeDatas().iterator(); it.hasNext(); ) {
      FlowProcRelativeData procRelativeData = (FlowProcRelativeData) it.next();
      if (procRelativeData.getFlowNodeOutputParamBinding() != null &&
          procRelativeData.getFlowNodeOutputParamBinding().
          getWfDriverOutputParam().getParamName().equals(driverParamName)) {
        return procRelativeData;
      }
    }
    return null;
  }

  public void setFlowProcRelativeDatas(List flowProcRelativeDatas) {
    this.flowProcRelativeDatas = flowProcRelativeDatas;
  }

  /**
   * @hibernate.bag name="linkedFlowProcs"
   * cascade="save-update"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_LINK_FLOW_PROC_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowProc"
   * @return List
   */
  public java.util.List getLinkedFlowProcs() {
    return linkedFlowProcs;
  }

  public void removeLinkedFlowProc(FlowProc flowProc) {
    flowProc.setLinkFlowProc(null);
    this.getLinkedFlowProcs().remove(flowProc);
  }

  public void addLinkedFlowProc(FlowProc flowProc) {
    flowProc.setLinkFlowProc(this);
    getLinkedFlowProcs().add(flowProc);
  }

  public void setLinkedFlowProcs(List linkedFlowProcs) {
    this.linkedFlowProcs = linkedFlowProcs;
  }

  /**
   * @hibernate.bag name="flowProcTransactions"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_PROC_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowProcTransaction"
   * @return List
   */
  public List getFlowProcTransactions() {
    return flowProcTransactions;
  }

  public void addProcTransaction(FlowProcTransaction procTransaction) {
    procTransaction.setFlowProc(this);
    getFlowProcTransactions().add(procTransaction);
  }

  public void removeProcTransaction(FlowProcTransaction procTransaction) {
    procTransaction.setFlowProc(null);
    getFlowProcTransactions().remove(procTransaction);
  }

  public void setFlowProcTransactions(List flowProcTransactions) {
    this.flowProcTransactions = flowProcTransactions;
  }

  /**
   * 变成孤儿（以便于删除）
   */
  public void toOrphan() {
    if (getFlowDeploy() != null) {
      getFlowDeploy().removeFlowProc(this);
    }
    if (getLinkFlowProc() != null) {
      getLinkFlowProc().removeLinkedFlowProc(this);
    }
  }

  public void clear() {
    if (this.getFlowProcRelativeDatas().size() > 0) {
      for (Iterator it = getFlowProcRelativeDatas().iterator(); it.hasNext(); ) {
        FlowProcRelativeData relativeData = (FlowProcRelativeData) it.next();
        relativeData.setFlowProc(null);
      }
      getFlowProcRelativeDatas().clear();
    }
    if (this.getFlowProcTransactions().size() > 0) {
      for (Iterator it = getFlowProcTransactions().iterator(); it.hasNext(); ) {
        FlowProcTransaction procTransaction = (FlowProcTransaction) it.next();
        procTransaction.setFlowProc(null);
        procTransaction.clear();
      }
      getFlowProcTransactions().clear();
    }
  }

  public String getPreviewText() {
    return previewText;
  }

  public void setPreviewText(String previewText) {
    this.previewText = previewText;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowProc)) {
      return false;
    }
    FlowProc fp = (FlowProc) object;
    return new EqualsBuilder().
        append(this.getFlowProcID().toString(), fp.getFlowProcID().toString())
        .append(this.getStartTime(), fp.getStartTime())
        .append(this.getStarterUserID(), fp.starterUserID)
        .isEquals();
  }

  public int hashCode() { //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(286335803, 287569255)
        .append(this.getFlowProcID().toString())
        .append(this.getStartTime())
        .append(this.getStarterUserID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("flowProcID", this.getFlowProcID().toString())
        .append("startTime", this.getStartTime())
        .append("starterUserID", this.getStarterUserID())
        .toString();
  }

}
