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

/**工作流部署的节点输入参数绑定信息
 * @hibernate.class table="WF_NODE_IN_PARAM_BIND"
 * <p>Title: PowerStone</p>
 */

public class FlowNodeInputParamBinding
    extends BaseObject {
  private Long paramBindingID = new Long( -1);
  private FlowNodeBinding flowNodeBinding;
  private String flowNodeParamID;
  private WFDriverInputParam wfDriverInputParam;

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

  /**
   * @hibernate.many-to-one
   * column="FK_DRIVER_INPUT_PARAM_ID"
   * class="org.powerstone.workflow.model.WFDriverInputParam"
   * @return WFDriverInputParam
   */
  public WFDriverInputParam getWfDriverInputParam() {
    return wfDriverInputParam;
  }

  public void setWfDriverInputParam(WFDriverInputParam wfDriverInputParam) {
    this.wfDriverInputParam = wfDriverInputParam;
  }

//------------------------------------------------------------------------------
  public boolean equals(Object object) {
    if (! (object instanceof FlowNodeInputParamBinding)) {
      return false;
    }
    FlowNodeInputParamBinding fnb = (FlowNodeInputParamBinding) object;
    return new EqualsBuilder().
        append(this.getParamBindingID().toString(),
               fnb.getParamBindingID().toString())
        .append(this.getFlowNodeParamID(), fnb.getFlowNodeParamID()).isEquals();
  }

  public int hashCode() {
    //随机选择两个奇数，每个类不同
    return new HashCodeBuilder(1456335803, 147569255).append(
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
