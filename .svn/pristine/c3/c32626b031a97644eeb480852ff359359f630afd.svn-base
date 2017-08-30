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

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Iterator;
import org.powerstone.workflow.exception.FlowDeployException;
import org.powerstone.workflow.exception.ExceptionMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @hibernate.class table="WF_FLOW_DEPLOY"
 */

public class FlowDeploy
    extends BaseObject {
  public static final String DEPLOY_STATE_PREPARING = "preparing";
  public static final String DEPLOY_STATE_RUNNING = "running";
  public static final String DEPLOY_STATE_READY = "ready";
  private static Log log = LogFactory.getLog(FlowDeploy.class);

  private Long flowDeployID = new Long( -1);
  private String flowDeployName;
  private String createTime;
  private String currentState;
  private String memo;
  private WorkflowMeta workflowMeta;
  private List flowNodeBindings = new ArrayList();
  private List flowProcs = new ArrayList();
  public FlowDeploy() {
  }

  /**
   * @hibernate.id column="PK_FLOW_DEPLOY_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getFlowDeployID() {
    return flowDeployID;
  }

  public void setFlowDeployID(Long flowDeployID) {
    this.flowDeployID = flowDeployID;
  }

  /**
   * @hibernate.property
   * 		column="VC_DEPLOY_NAME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getFlowDeployName() {
    return flowDeployName;
  }

  public void setFlowDeployName(String flowDeployName) {
    this.flowDeployName = flowDeployName;
  }

  /**
   * @hibernate.property
   * 		column="VC_CREATE_TIME"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  /**
   * @hibernate.property
   * 		column="VC_CURRENT_STATE"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getCurrentState() {
    return currentState;
  }

  /**
   * 部署是否可运行
   * @return boolean
   */
  public boolean isReady() {
    return (getCurrentState() != null &&
            getCurrentState().equals(this.DEPLOY_STATE_READY));
  }

  public void enableFlowDeploy() {
    setCurrentState(DEPLOY_STATE_READY);
  }

  public void disableFlowDeploy() {
    setCurrentState(DEPLOY_STATE_PREPARING);
  }

  public void setCurrentState(String currentState) {
    this.currentState = currentState;
  }

  /**
   * @hibernate.property
   * 		column="VC_MEMO"
   * 		length="255"
   * 		type="string"
   * @return String
   */
  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_META_ID"
   * class="org.powerstone.workflow.model.WorkflowMeta"
   * @return WorkflowMeta
   */
  public WorkflowMeta getWorkflowMeta() {
    return workflowMeta;
  }

  public void setWorkflowMeta(WorkflowMeta workflowMeta) {
    this.workflowMeta = workflowMeta;
  }

  /**
   * @hibernate.bag name="flowNodeBindings"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_DEPLOY_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowNodeBinding"
   * @return List
   */
  public List getFlowNodeBindings() {
    return flowNodeBindings;
  }

  public void setFlowNodeBindings(List flowNodeBindings) {
    this.flowNodeBindings = flowNodeBindings;
  }

  public void addFlowNodeBinding(FlowNodeBinding flowNodeBinding) {
    if (getFlowNodeBindings().size() > 0) {
      boolean created = false;
      for (Iterator it = getFlowNodeBindings().iterator(); it.hasNext(); ) {
        FlowNodeBinding fnb = (FlowNodeBinding) it.next();
        if (fnb.getFlowNodeID().equals(flowNodeBinding.getFlowNodeID())) {
          fnb.setWorkflowDriver(flowNodeBinding.getWorkflowDriver());
          created = true;
        }
        else {
          if (fnb.getWorkflowDriver() != null &&
              fnb.getWorkflowDriver().equals(flowNodeBinding.getWorkflowDriver())) {
            throw new FlowDeployException(ExceptionMessage.
                                          ERROR_FLOWDEPLOY_DRIVER_REUSEE);
          }
        }
      }
      if (created) {
        return;
      }
    }

    getFlowNodeBindings().add(flowNodeBinding);
    flowNodeBinding.setFlowDeploy(this);
  }

  /**
   * @hibernate.bag name="flowProcs"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_FLOW_DEPLOY_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowProc"
   * @return List
   */
  public List getFlowProcs() { //cascade="save-update"
    return flowProcs;
  }

  public void removeFlowProc(FlowProc flowProc) {
    flowProc.setFlowDeploy(null);
    this.getFlowProcs().remove(flowProc);
  }

  public void removeAllFlowProcs() {
    for (Iterator it = getFlowProcs().iterator(); it.hasNext(); ) {
      FlowProc flowProc = (FlowProc) it.next();
      flowProc.setFlowDeploy(null);
    }
    this.getFlowProcs().clear();
  }

  public void addFlowProc(FlowProc flowProc) {
    flowProc.setFlowDeploy(this);
    getFlowProcs().add(flowProc);
  }

  public void setFlowProcs(List flowProcs) {
    this.flowProcs = flowProcs;
  }

  public void clear() {
    if (this.getFlowProcs().size() > 0) {
      for (Iterator it = getFlowProcs().iterator(); it.hasNext(); ) {
        FlowProc flowProc = (FlowProc) it.next();
        //flowProc.clear();
        flowProc.setFlowDeploy(null);
      }
      getFlowProcs().clear();
    }
    if (this.getFlowNodeBindings().size() > 0) {
      for (Iterator it = getFlowNodeBindings().iterator(); it.hasNext(); ) {
        FlowNodeBinding flowNodeBinding = (FlowNodeBinding) it.next();
        flowNodeBinding.setFlowDeploy(null);
      }
      getFlowNodeBindings().clear();
    }
  }

  public FlowNodeBinding getFlowNodeBindingByNodeID(String nodeID) {
    for (Iterator it = this.getFlowNodeBindings().iterator(); it.hasNext(); ) {
      FlowNodeBinding fnb = (FlowNodeBinding) it.next();
      if (fnb.getFlowNodeID().equals(nodeID)) {
        return fnb;
      }
    }
    return null;
  }

  public FlowNodeOutputParamBinding findNodeOutputParamBindingByDriver(
      WorkflowDriver flowDriver, String driverParamName) {
    if (log.isDebugEnabled()) {
      log.debug("flowDriver[" + flowDriver + "]|driverParamName[" +
                driverParamName + "]");
    }
    for (Iterator it = this.getFlowNodeBindings().iterator(); it.hasNext(); ) {
      FlowNodeBinding fnb = (FlowNodeBinding) it.next();
      if (fnb.getWorkflowDriver().equals(flowDriver)) {
        FlowNodeOutputParamBinding result =
            fnb.findNodeOutputParamBindingByDriverParamName(driverParamName);
        if (log.isDebugEnabled()) {
          log.debug("result[" + result + "]");
        }
        return result;
      }
    }
    return null;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowDeploy)) {
      return false;
    }
    FlowDeploy fd = (FlowDeploy) object;
    return new EqualsBuilder()
        .append(this.getFlowDeployID().toString(),
                fd.getFlowDeployID().toString())
        .append(this.getFlowDeployName(), fd.getFlowDeployName())
        .append(this.getMemo(), fd.getMemo()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1156335803, 117569255).append(
        this.getFlowDeployID().toString())
        .append(this.getFlowDeployName())
        .append(this.getMemo()).toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("flowDeployID", this.getFlowDeployID().toString())
        .append("flowDeployName", this.getFlowDeployName())
        .append("memo", this.getMemo()).toString();
  }

}
