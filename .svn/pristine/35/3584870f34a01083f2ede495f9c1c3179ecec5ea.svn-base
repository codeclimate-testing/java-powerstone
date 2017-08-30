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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**记录一个工作流进程已经走过的路径
 * @hibernate.class table="WF_FLOW_PROC_TRANSITION"
 * <p>Title: PowerStone</p>
 */

public class FlowProcTransition extends BaseObject {
  private static Log log = LogFactory.getLog(FlowProcTransition.class);
  private Long id = new Long( -1);
  private String fromNodeID;
  private String workflowTransitionID;
  private String toNodeID;
  private String conditionType;
  private String conditionExpress;
  private FlowProcTransaction flowProcTransaction;
  /**
   * @hibernate.id column="PK_FLOW_PROC_TRANSITION_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  /**
   * @hibernate.property
   * 		column="VC_FROM_NODE_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getFromNodeID() {
    return fromNodeID;
  }
  public void setFromNodeID(String fromNodeID) {
    this.fromNodeID = fromNodeID;
  }

  /**
   * @hibernate.property
   * 		column="VC_WORKFLOW_TRANSITION_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getWorkflowTransitionID() {
    return workflowTransitionID;
  }
  public void setWorkflowTransitionID(String workflowTransitionID) {
    this.workflowTransitionID = workflowTransitionID;
  }
  /**
   * @hibernate.property
   * 		column="VC_TO_NODE_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getToNodeID() {
    return toNodeID;
  }
  public void setToNodeID(String toNodeID) {
    this.toNodeID = toNodeID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_FLOW_PROC_TRANSACTION_ID"
   * class="org.powerstone.workflow.model.FlowProcTransaction"
   * @return FlowProcTransaction
   */
  public FlowProcTransaction getFlowProcTransaction() {
    return flowProcTransaction;
  }
  public void setFlowProcTransaction(FlowProcTransaction flowProcTransaction) {
    this.flowProcTransaction = flowProcTransaction;
  }


  public String getConditionType() {
    return conditionType;
  }
  public void setConditionType(String conditionType) {
    this.conditionType = conditionType;
  }
  public String getConditionExpress() {
    return conditionExpress;
  }
  public void setConditionExpress(String conditionExpress) {
    this.conditionExpress = conditionExpress;
  }
//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowProcTransition)) {
      return false;
    }
    FlowProcTransition fnt = (FlowProcTransition) object;
    if(log.isDebugEnabled()){
      log.debug("["+this+"--------\n--------"+fnt+"]");
    }
    return new EqualsBuilder().
        append(this.getWorkflowTransitionID(),fnt.getWorkflowTransitionID())
        .append(this.getToNodeID(), fnt.getToNodeID())
        .append(this.getFromNodeID(), fnt.getFromNodeID())
        .isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(2056335803, 207569255)
        .append(this.getWorkflowTransitionID().toString())
        .append(this.getToNodeID())
        .append(this.getFromNodeID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("TransitionID", this.getWorkflowTransitionID())
        .append("toNodeID", this.getToNodeID())
        .append("fromNodeID", this.getFromNodeID())
        .toString();
  }
}
