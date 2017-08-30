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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.Iterator;

/**工作流部署的节点输出参数绑定信息
 * @hibernate.class table="WF_NODE_OUT_PARAM_BIND"
 * <p>Title: PowerStone</p>
 */

public class FlowNodeOutputParamBinding
    extends BaseObject {
  private Long paramBindingID = new Long( -1);
  private FlowNodeBinding flowNodeBinding;
  private WFDriverOutputParam wfDriverOutputParam;
  private List flowNodeOutputParamEnumeBindings = new ArrayList();
  private String flowNodeParamID;

  /**
   * @hibernate.id column="PK_PARAM_BINDING_ID"
   * 		   unsaved-value="-1"
   *               generator-class="native"
   * @return Long
   */
  public Long getParamBindingID() {
    return paramBindingID;
  }

  public void setParamBindingID(Long paramBindingID) {
    this.paramBindingID = paramBindingID;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_NODE_BINDING_ID"
   * class="org.powerstone.workflow.model.FlowNodeBinding"
   * @return FlowNodeBinding
   */
  public FlowNodeBinding getFlowNodeBinding() {
    return flowNodeBinding;
  }

  public void setFlowNodeBinding(FlowNodeBinding flowNodeBinding) {
    this.flowNodeBinding = flowNodeBinding;
  }

  /**
   * @hibernate.many-to-one
   * column="FK_DRIVER_OUTPUT_PARAM_ID"
   * class="org.powerstone.workflow.model.WFDriverOutputParam"
   * @return WFDriverOutputParam
   */
  public WFDriverOutputParam getWfDriverOutputParam() {
    return wfDriverOutputParam;
  }

  public void setWfDriverOutputParam(WFDriverOutputParam wfDriverOutputParam) {
    this.wfDriverOutputParam = wfDriverOutputParam;
  }

  /**
   * @hibernate.bag name="flowNodeOutputParamEnumeBindings"
   * cascade="all-delete-orphan"
   * lazy="true"
   * inverse="true"
   * @hibernate.collection-key
   * column="FK_PARAM_BINDING_ID"
   * @hibernate.collection-one-to-many
   * class="org.powerstone.workflow.model.FlowNodeOutputParamEnumeBinding"
   * @return List
   */
  public List getFlowNodeOutputParamEnumeBindings() {
    return flowNodeOutputParamEnumeBindings;
  }

  public void addFlowNodeOutputParamEnumeBinding(
      FlowNodeOutputParamEnumeBinding nodeOutputParamEnumeBinding) {
    nodeOutputParamEnumeBinding.setFlowNodeOutputParamBinding(this);
    getFlowNodeOutputParamEnumeBindings().add(nodeOutputParamEnumeBinding);
  }

  public WFDriverOutputParamEnume findDriverOutputParamEnumeByNodeParamEnume(
      String nodeOutputParamEnume) {
    for (Iterator it = getFlowNodeOutputParamEnumeBindings().iterator();
         it.hasNext(); ) {
      FlowNodeOutputParamEnumeBinding nodeOutputParamEnumeBinding =
          (FlowNodeOutputParamEnumeBinding) it.next();
      if (nodeOutputParamEnumeBinding.getNodeOutputParamEnume().equals(
          nodeOutputParamEnume)) {
        return nodeOutputParamEnumeBinding.getWfDriverOutputParamEnume();
      }
    }
    return null;
  }

  public FlowNodeOutputParamBinding findNodeOutputParamEnumeByDriverParamEnume(
      String driverOutputParamEnume) {
    for (Iterator it = getFlowNodeOutputParamEnumeBindings().iterator();
         it.hasNext(); ) {
      FlowNodeOutputParamEnumeBinding nodeOutputParamEnumeBinding =
          (FlowNodeOutputParamEnumeBinding) it.next();
      if (nodeOutputParamEnumeBinding.getWfDriverOutputParamEnume().equals(
          driverOutputParamEnume)) {
        return nodeOutputParamEnumeBinding.getFlowNodeOutputParamBinding();
      }
    }
    return null;
  }

  public FlowNodeOutputParamEnumeBinding
      findNodeOutputParamEnumeBindingByNodeParamEnume(
      String nodeOutputParamEnume) {
    for (Iterator it = getFlowNodeOutputParamEnumeBindings().iterator();
         it.hasNext(); ) {
      FlowNodeOutputParamEnumeBinding nodeOutputParamEnumeBinding =
          (FlowNodeOutputParamEnumeBinding) it.next();
      if (nodeOutputParamEnumeBinding.getNodeOutputParamEnume().equals(
          nodeOutputParamEnume)) {
        return nodeOutputParamEnumeBinding;
      }
    }
    return null;
  }

  public void setFlowNodeOutputParamEnumeBindings(java.util.List
                                                  flowNodeOutputParamEnumeBindings) {
    this.flowNodeOutputParamEnumeBindings = flowNodeOutputParamEnumeBindings;
  }

  /**
   * @hibernate.property
   * 		column="VC_NODE_PARAM_ID"
   * 		length="255"
   * 		type="string"
   *            not-null="true"
   * @return String
   */
  public String getFlowNodeParamID() {
    return flowNodeParamID;
  }

  public void setFlowNodeParamID(String flowNodeParamID) {
    this.flowNodeParamID = flowNodeParamID;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowNodeOutputParamBinding)) {
      return false;
    }
    FlowNodeOutputParamBinding fnb = (FlowNodeOutputParamBinding) object;
    return new EqualsBuilder().
        append(this.getParamBindingID().toString(), fnb.getParamBindingID().toString())
        .append(this.getFlowNodeParamID(), fnb.getFlowNodeParamID()).isEquals();
  }

  public int hashCode() {//随机选择两个奇数，每个类不同
    return new HashCodeBuilder(266335803, 267569255).append(
        this.getParamBindingID().toString()).append(this.getFlowNodeParamID())
        .toHashCode();
  }

  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("paramBindingID", this.getParamBindingID().toString())
        .append("flowNodeParamID", this.getFlowNodeParamID())
        .toString();
  }

}
